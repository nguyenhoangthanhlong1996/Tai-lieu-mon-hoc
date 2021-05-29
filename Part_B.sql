/* 
    Part B
    Student ID: 1481300
*/
----------------------------------------------------- (1) ---------------------------------------------------------
select m.movie_id, m.movie_title, m.movie_cat_id, mt.movie_category
from mm_movie m
left join mm_movie_type mt on m.movie_cat_id = mt.movie_cat_id;

----------------------------------------------------- (2) ---------------------------------------------------------
select member_id, last as lastname, suspension
from mm_member;

----------------------------------------------------- (3) ---------------------------------------------------------
select count(*) as numbers_of_members_suspended
from mm_member
where suspension = 'Y';

----------------------------------------------------- (4) ---------------------------------------------------------
select me.last as member_lastname, r.checkout_date, mo.movie_title
from mm_rental r
left join mm_member me on r.member_id = me.member_id
left join mm_movie mo on r.movie_id = mo.movie_id;

----------------------------------------------------- (5) ---------------------------------------------------------
select m.movie_id, m.movie_title, mt.movie_category, r.number_of_rentals
from (
    -- Count total rating per movie and order rating desc
    select movie_id, count(*) as number_of_rentals
    from mm_rental
    group by movie_id
    order by count(*) desc
) r
left join mm_movie m on r.movie_id = m.movie_id
left join mm_movie_type mt on m.movie_cat_id = mt.movie_cat_id
-- Top 3
where rownum <= 3;

----------------------------------------------------- (6) ---------------------------------------------------------
select me.member_id, mo.movie_title as movie_rented
from mm_member me
left join mm_rental r on me.member_id = r.member_id
left join mm_movie mo on r.movie_id = mo.movie_id;

----------------------------------------------------- (7) ---------------------------------------------------------
select m.movie_title, count(*) as number_of_rentals
from mm_movie m
inner join mm_rental r on m.movie_id = r.movie_id
group by m.movie_id, m.movie_title
having count(*) > nvl((
    select count(*) as number_of_rentals
    from mm_movie m
    inner join mm_rental r on m.movie_id = r.movie_id
    where m.movie_title = 'Silverado'
    group by r.movie_id
), 0);

----------------------------------------------------- (8) ---------------------------------------------------------
---- Create Sequence
CREATE SEQUENCE memberID_seq_no
INCREMENT BY 1
START WITH 100
NOCYCLE
NOCACHE;
---- Test Sequence
insert into mm_member(member_id, last, first, license_no, license_st, credit_card, suspension)
values (MEMBERID_SEQ_NO.nextval, 'Pearson', 'Charles', '666666666', 'VIC', '757575757575', 'N');
