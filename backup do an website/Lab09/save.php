<?php 
    require "Database.php";
    $db = new Database;

    if (isset($_GET['id']) && isset($_GET['name']) && isset($_GET['year'])) {
        $id = $_GET['id'];
        $name = $_GET['name'];
        $year = $_GET['year'];
        session_start();
        if (strlen($name) < 5 || strlen($name) > 40 || $year < 1990 || $year > 2015) {
            $_SESSION["message"] = "Dữ liệu không hợp lệ (tên từ 5 đến 40 kí tự, năm sản xuất trong khoảng 1990 - 2015)";
        } else {
            if (empty($id)) { //field id trống => đây là yêu cầu lưu mới
                $sql = "INSERT INTO `cars` (`id`, `name`, `year`) VALUES (NULL, '$name', '$year')";
                if (!$db->query($sql)) {
                    $_SESSION["message"] = "Không thể lưu";
                } else {
                    $_SESSION["message"] = "Lưu thông tin xe thành công";
                }
            } else { //field id có chứa giá trị id của xe muốn sửa => yêu cầu lưu cập nhật
                $sql = "UPDATE `cars` SET `name` = '$name', `year` = '$year' WHERE `cars`.`id` = $id";
                if (!$db->query($sql)) {
                    $_SESSION["message"] = "Không thể lưu";
                } else {
                    $_SESSION["message"] = "Lưu thông tin xe thành công";
                }
            }
        }
    }
    header('Location: index.php');
?>