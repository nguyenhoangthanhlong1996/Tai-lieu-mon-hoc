<?php 
    require "Database.php";
    $db = new Database;

    if (isset($_GET['id']) && isset($_GET['name']) && isset($_GET['year'])) {
        $id = $_GET['id'];
        $name = $_GET['name'];
        $year = $_GET['year'];
        if (empty($id)) { //field id trống => đây là yêu cầu lưu mới
            $sql = "INSERT INTO `cars` (`id`, `name`, `year`) VALUES (NULL, '$name', '$year')";
            if (!$db->query($sql)) {
                echo "lỗi";
            }
        } else { //field id có chứa giá trị id của xe muốn sửa => yêu cầu lưu cập nhật
            $sql = "UPDATE `cars` SET `name` = '$name', `year` = '$year' WHERE `cars`.`id` = $id";
            if (!$db->query($sql)) {
                echo "lỗi";
            }
        }
        session_start();
        $_SESSION["message"] = "Lưu thông tin xe thành công";
        header('Location: index.php');
    }
?>