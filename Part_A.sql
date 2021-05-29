/* 
    Part A
    Student ID: 1481300
*/
----------------------------------------------------- (1) ---------------------------------------------------------
---- Create Procedure
CREATE OR REPLACE PROCEDURE p__print_memberInfo_1481300(i_member_id number)
AS
    v_fname mm_member.first%type;
    v_lname mm_member.last%type;
    v_licenseNo mm_member.license_no%type;
BEGIN
    select first, last, license_no into v_fname, v_lname, v_licenseNo
    from mm_member
    where member_id = i_member_id;
    
    DBMS_OUTPUT.PUT_LINE('First name: ' || v_fname);
    DBMS_OUTPUT.PUT_LINE('Last name: ' || v_lname);
    DBMS_OUTPUT.PUT_LINE('License No: ' || v_licenseNo);
    
    EXCEPTION WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('Member ID does not exist.');
END;
---- Test the procedure for member ID = 14
SET SERVEROUTPUT ON;
DECLARE
BEGIN
    p__print_memberInfo_1481300(14);
END;

----------------------------------------------------- (2) ---------------------------------------------------------
SET SERVEROUTPUT ON;
DECLARE
    -- Init movie_id
    v_movieId mm_movie.movie_id%type := 11;
    --
    v_movieTitle mm_movie.movie_title%type;
    v_rentalCount number;
    v_rentalRating varchar2(25);
BEGIN
    -- Get movie title
    select movie_title into v_movieTitle
    from mm_movie
    where movie_id = v_movieId;
    -- Count number of rental
    select count(*) into v_rentalCount
    from mm_rental
    where movie_id = v_movieId;
    -- Rating
    v_rentalRating := case
        when v_rentalCount < 5 then 'Flop'
        when v_rentalCount < 21 then 'Low'
        when v_rentalCount < 36 then 'Mid'
        else 'Mid'
    end;
    -- Print
    DBMS_OUTPUT.PUT_LINE('Movie title: ' || v_movieTitle);
    DBMS_OUTPUT.PUT_LINE('Rental count: ' || v_rentalCount);
    DBMS_OUTPUT.PUT_LINE('Rental rating: ' || v_rentalRating);
    
    EXCEPTION WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('Movie ID does not exist.');
END;

----------------------------------------------------- (3) ---------------------------------------------------------
---- Create Function
CREATE OR REPLACE FUNCTION f__check_memberExist_1481300(i_firstName varchar2) RETURN VARCHAR2 
AS
    v_count number;
BEGIN
    select count(*) into v_count
    from mm_member
    where first = i_firstName;
    
    RETURN case when v_count > 0 then 'TRUE' else 'FALSE' end;
END;
---- Test Function
SET SERVEROUTPUT ON;
DECLARE
    v_firstName mm_member.first%type := 'Coyote';
BEGIN
    -- Print
    DBMS_OUTPUT.PUT_LINE('Exist member has first name is "' || v_firstName || '": ' || f__check_memberExist_1481300(v_firstName));
END;

----------------------------------------------------- (4) ---------------------------------------------------------
---- Create Function
CREATE OR REPLACE FUNCTION f__check_memberSuspended_1481300(i_memberId number) RETURN VARCHAR2 
AS
    v_suspension mm_member.suspension%type;
BEGIN
    select suspension into v_suspension
    from mm_member
    where member_id = i_memberId;
    
    RETURN case when v_suspension = 'Y' then 'TRUE' else 'FALSE' end;
    
    EXCEPTION WHEN NO_DATA_FOUND THEN RETURN 'FALSE';
END;
---- Test Function
SET SERVEROUTPUT ON;
DECLARE
    v_memberId mm_member.member_id%type := 11;
BEGIN
    -- Print
    DBMS_OUTPUT.PUT_LINE('Member has ID equal ' || v_memberId || ' is suspended: ' || f__check_memberSuspended_1481300(v_memberId));
END;

----------------------------------------------------- (5) ---------------------------------------------------------
------------------ (a) Rental - Checkout
---- Create Function
CREATE OR REPLACE PROCEDURE p__rental_checkout_1481300(i_memberId number, i_movieId number, i_paymentMethodId number, o_flagError out number, o_message out varchar2)
AS
    v_tmp number;
    v_movieQty mm_movie.movie_qty%type;
BEGIN
    -- Check Member ID is valid
    o_message := 'Member ID does not exist.';
    select member_id into v_tmp from mm_member where member_id = i_memberId;
    -- Check Movie ID is valid
    o_message := 'Movie ID does not exist.';
    select movie_qty into v_movieQty from mm_movie where movie_id = i_movieId;
    -- Check Payment Method ID is valid
    o_message := 'Payment Method ID does not exist.';
    select payment_methods_id into v_tmp from mm_pay_type where payment_methods_id = i_paymentMethodId;
    -- Check Movie Qty > 0
    if (v_movieQty <= 0) then
        o_flagError := 1;
        o_message := 'Movie quantity not enough.';
        return;
    end if;
    -- Create 1 record rental
    insert into mm_rental (rental_id, member_id, movie_id, payment_methods_id, checkout_date)
    values (mm_rental_seq.nextval, i_memberId, i_movieId, i_paymentMethodId, sysdate);
    -- Update Movie Qty
    update mm_movie 
    set movie_qty = v_movieQty - 1
    where movie_id = i_movieId;
    -- Set flag error (o_flagError = 0 -> Success, o_flagError = 1 -> Has error)
    o_flagError := 0;
    
    EXCEPTION WHEN NO_DATA_FOUND THEN o_flagError := 1;
END;
---- Test Function
SET SERVEROUTPUT ON;
DECLARE
    v_memberId mm_member.member_id%type := 13;
    v_movieId mm_movie.movie_id%type := 12;
    v_paymentMethodId mm_pay_type.payment_methods_id%type := 4;
    --
    v_out_flagError number;
    v_out_message varchar2(255);
BEGIN
    p__rental_checkout_1481300(v_memberId, v_movieId, v_paymentMethodId, v_out_flagError, v_out_message);
    if (v_out_flagError = 0) then
        -- Print
        DBMS_OUTPUT.PUT_LINE('[Success]: Rental created.');    
    else
        -- Print
        DBMS_OUTPUT.PUT_LINE('[Error]: ' || v_out_message);    
    end if;
END;
------------------ (a) Rental - Checkin
---- Create Function
CREATE OR REPLACE PROCEDURE p__rental_checkin_1481300(i_memberId number, i_movieId number, o_flagError out number, o_message out varchar2)
AS
    v_tmp number;
    v_movieQty mm_movie.movie_qty%type;
    v_rentalId mm_rental.rental_id%type;
BEGIN
    -- Check Rental is valid
    o_message := 'Can not found Rental by Member ID and Movie ID.';
    select rental_id into v_rentalId 
    from mm_rental 
    where member_id = i_memberId and movie_id = i_movieId and checkin_date is null;
    -- Get Movie Qty
    select movie_qty into v_movieQty from mm_movie where movie_id = i_movieId;
    -- Update rental record
    update mm_rental
    set checkin_date = sysdate
    where rental_id = v_rentalId;
    -- Update Movie Qty
    update mm_movie 
    set movie_qty = v_movieQty + 1
    where movie_id = i_movieId;
    -- Set flag error (o_flagError = 0 -> Success, o_flagError = 1 -> Has error)
    o_flagError := 0;
    
    EXCEPTION WHEN NO_DATA_FOUND THEN o_flagError := 1;
END;
---- Test Function
SET SERVEROUTPUT ON;
DECLARE
    v_memberId mm_member.member_id%type := 13;
    v_movieId mm_movie.movie_id%type := 12;
    --
    v_out_flagError number;
    v_out_message varchar2(255);
BEGIN
    p__rental_checkin_1481300(v_memberId, v_movieId, v_out_flagError, v_out_message);
    if (v_out_flagError = 0) then
        -- Print
        DBMS_OUTPUT.PUT_LINE('[Success]: Movie''s return success.');    
    else
        -- Print
        DBMS_OUTPUT.PUT_LINE('[Error]: ' || v_out_message);    
    end if;
END;

----------------------------------------------------- (6) ---------------------------------------------------------
---- Create Function
CREATE OR REPLACE FUNCTION f__get_moviesQuantity_1481300(i_movieId number) RETURN VARCHAR2
AS
    v_movieTitle mm_movie.movie_title%type;
    v_movieQty mm_movie.movie_qty%type;
BEGIN
    select movie_title, movie_qty into v_movieTitle, v_movieQty
    from mm_movie
    where movie_id = i_movieId;
    
    RETURN v_movieTitle || ' is Available: ' || v_movieQty || ' on the shelf.';
    
    EXCEPTION WHEN NO_DATA_FOUND THEN RETURN 'Movie ID does not exist.';
END;
---- Test Function
SET SERVEROUTPUT ON;
DECLARE
    v_movieId mm_movie.movie_id%type := 4;
BEGIN
    -- Print
    DBMS_OUTPUT.PUT_LINE(f__get_moviesQuantity_1481300(v_movieId));    
END;

----------------------------------------------------- (7) ---------------------------------------------------------
---------------------------------- Trigger for Rentals
---- Create Trigger
CREATE OR REPLACE TRIGGER AI__rentals_1481300
AFTER INSERT ON mm_rental FOR EACH ROW
DECLARE
BEGIN
    update mm_movie 
    set movie_qty = movie_qty - 1
    where movie_id = :new.movie_id;
END;
---- Test Trigger
INSERT INTO mm_rental (rental_id, member_id, movie_id, payment_methods_id) VALUES (mm_rental_seq.nextval, 10, 6, 2);
---------------------------------- Trigger for Returns
---- Create Trigger
CREATE OR REPLACE TRIGGER AU__returns_1481300
AFTER UPDATE ON mm_rental FOR EACH ROW
DECLARE
BEGIN
    IF (:old.checkin_date is null and :new.checkin_date is not null) THEN
        update mm_movie 
        set movie_qty = movie_qty + 1
        where movie_id = :old.movie_id;
    END IF;
END;
---- Test Trigger
UPDATE mm_rental SET checkin_date = SYSDATE WHERE rental_id = 14;