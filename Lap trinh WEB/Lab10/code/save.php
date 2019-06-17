<?php
    $response = array();
    if (isset($_REQUEST['id']) && isset($_REQUEST['name']) && isset($_REQUEST['year'])) {
        $id = $_REQUEST['id'];
        $name = $_REQUEST['name'];
        $year = $_REQUEST['year'];
        if (strlen($name) < 5 || strlen($name) > 40 || $year < 1990 || $year > 2015) {
            $response['success'] = 0;
            $response['msg'] = 'Dữ liệu không hợp lệ (tên từ 5 đến 40 kí tự, năm sản xuất trong khoảng 1990 - 2015)';
            $response['data'] = '';
        } else {
            require "Database.php";
            $db = new Database;
            if (empty($id)) { //field id trống => đây là yêu cầu lưu mới
                $sql = "INSERT INTO `cars` (`id`, `name`, `year`) VALUES (NULL, '$name', '$year')";
                if (!$db->query($sql)) {
                    $response['success'] = 0;
                    $response['msg'] = 'Lỗi trong quá trình lưu thông tin';
                    $response['data'] = '';
                } else {
                    $response['success'] = 1;
                    $response['msg'] = 'Lưu thông tin xe thành công';
                    $response['data'] = '';
                }
            } else { //field id có chứa giá trị id của xe muốn sửa => yêu cầu lưu cập nhật
                $sql = "UPDATE `cars` SET `name` = '$name', `year` = '$year' WHERE `cars`.`id` = $id";
                if (!$db->query($sql)) {
                    $response['success'] = 0;
                    $response['msg'] = 'Lỗi trong quá trình lưu thông tin';
                    $response['data'] = '';
                } else {
                    $response['success'] = 1;
                    $response['msg'] = 'Lưu thông tin xe thành công';
                    $response['data'] = '';
                }
            }
        }
    } else {
        $response['success'] = 0;
        $response['msg'] = 'Không đủ tham số';
        $response['data'] = '';
    }
    echo json_encode($response);
?>