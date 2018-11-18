<!--Bài 6 -->
<!DOCTYPE html>
<?php
    //Khai báo các biến dùng để chứa dữ liệu trả về cho người dùng
    $valid = false;
    $firstname = "";    $err_firstname = "";
    $lastname = "";     $err_lastname = "";
    $email = "";        $err_email = "";
    $password = "";     $err_password = "";
    $day = 0;           $err_birthday = "";
    $month = 0;
    $year = 0;
    $gender = "male";
    $country = 1;
    $about = "";        $err_about = "";
    //Kiem tra thong tin nguoi dung
    //Kiem tra first name
    if (isset($_POST['firstname'])) { $valid = true;
        $firstname = $_POST['firstname'];
        $length_firstname = strlen($firstname);
        if ($length_firstname < 2 || $length_firstname > 30) {
            $err_firstname = "Firstname phải từ 2 - 30 kí tự";
            $valid = false;
        }
    }
    //Kiem tra last name
    if (isset($_POST['lastname'])) {
        $lastname = $_POST['lastname'];
        $length_lastname = strlen($lastname);
        if ($length_lastname < 2 || $length_lastname > 30) {
            $err_lastname = "Lastname phải từ 2 - 30 kí tự";
            $valid = false;
        }
    }
    //Kiem tra email
    if (isset($_POST['email'])) {
        $email = $_POST['email'];
        if(!preg_match ("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+\.[A-Za-z]{2,6}$/", $email)) {
            $err_email = "Mail không đúng định dạng";
            $valid = false;
        }
    }
    //Kiem tra password
    if (isset($_POST['password'])) {
        $password = $_POST['password'];
        $length_password = strlen($password);
        if ($length_password < 2 || $length_password > 30) {
            $err_password = "Password phải từ 2 - 30 kí tự";
            $valid = false;
        }
    }
    //Kiểm tra birthday
    if (isset($_POST['day']) && isset($_POST['month']) && isset($_POST['year'])) {
        $day = $_POST['day'];
        $month = $_POST['month'];
        $year = $_POST['year'];
        if ($day != 0 && $month != 0 && $year !=0) {
            if (!checkdate($month,$day,$year)) {
                $err_birthday = "Birthday không hợp lệ";
                $valid = false;
            }
        } else {
            $err_birthday = "Birthday phải chọn đầy đủ ngày, tháng, năm";
            $valid = false;
        }
    }
    //Kiểm tra gender
    if (isset($_POST['gender'])) {
        $gender = $_POST['gender'];
    }
    //Kiểm tra country
    if (isset($_POST['country'])) {
        $country = $_POST['country'];
    }
    //Kiểm tra about
    if (isset($_POST['about'])) {
        $about = $_POST['about'];
        $length_about = strlen($about);
        if ($length_about > 10000) {
            $err_about = "About không được quá 10000 kí tự";
            $valid = false;
        }
    }
?>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 6</title>
</head>
<body>
    <form action="" method="post" style="float: left; margin-right:10px;">
        <table border="1">
            <tr>
                <td>First name</td>
                <td>
                    <input name="firstname" type="text" value="<?php echo $firstname; ?>"/>
                    <label for="firstname" style="color:red;"><?php echo $err_firstname; ?></label>
                </td>
            </tr>
            <tr>
                <td>Last name</td>
                <td>
                    <input name="lastname" type="text" value="<?php echo $lastname; ?>"/>
                    <label for="lastname" style="color:red;"><?php echo $err_lastname; ?></label>
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td>
                    <input name="email" type="email" value="<?php echo $email; ?>"/>
                    <label for="email" style="color:red;"><?php echo $err_email; ?></label>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input name="password" type="password" value="<?php echo $password; ?>"/>
                    <label for="password" style="color:red;"><?php echo $err_password; ?></label>
                </td>
            </tr>
            <tr>
                <td>Birtday</td>
                <td>
                    <select name="day">
                        <option value="0">--day--</option>
                        <?php for ($i=1;$i<=31;$i++) {
                            $selected = ($i == $day) ? 'selected' : '';
                            echo "<option value=$i $selected>$i</option>";
                        } ?>
                    </select>
                    <select name="month">
                        <option value="0">--month--</option>
                        <?php for ($i=1;$i<=12;$i++) {
                            $selected = ($i == $month) ? 'selected' : '';
                            echo "<option value=$i $selected>$i</option>";
                        } ?>
                    </select>
                    <select name="year">
                        <option value="0">--year--</option>
                        <?php for ($i=date('Y');$i>=date('Y')-100;$i--) {
                            $selected = ($i == $year) ? 'selected' : '';
                            echo "<option value=$i $selected>$i</option>";
                        } ?>
                    </select>
                    <label for="birthday" style="color:red;"><?php echo $err_birthday; ?></label>
                </td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <input type="radio" name="gender" value="male" <?php echo $gender == 'male' ? 'checked' : ''; ?>/>Male
                    <input type="radio" name="gender" value="female" <?php echo $gender == 'female' ? 'checked' : ''; ?>/>Female
                    <input type="radio" name="gender" value="nondefine" <?php echo $gender == 'nondefine' ? 'checked' : ''; ?>/>Non defining
                </td>
            </tr>
            <tr>
                <td>Country</td>
                <td>
                    <select name="country">
                        <option value="1" <?php echo $country == 1 ? 'selected' : ''; ?>>Vietnam</option>
                        <option value="2" <?php echo $country == 2 ? 'selected' : ''; ?>>Australia</option>
                        <option value="3" <?php echo $country == 3 ? 'selected' : ''; ?>>United States</option>
                        <option value="4" <?php echo $country == 4 ? 'selected' : ''; ?>>India</option>
                        <option value="5" <?php echo $country == 5 ? 'selected' : ''; ?>>Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>About</td>
                <td>
                    <textarea name="about" rows="5" cols="30"><?php echo $about; ?></textarea>
                    <label for="about" style="color:red;"><?php echo $err_about; ?></label>
                </td>
            </tr>
        </table>
        <input type="submit" name="" value="Submit"/>
        <input type="button" name="" value="Reset" onclick="window.location.href += '';"/>
    </form>
    <h4><?php echo $valid ? 'Complete !' : '' ?></h4>
</body>
</html>
