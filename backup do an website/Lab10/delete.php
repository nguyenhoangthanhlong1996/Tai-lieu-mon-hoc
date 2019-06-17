<?php
    $response = array();
    if (isset($_REQUEST['id'])) {
        $id = $_REQUEST['id'];
        require "Database.php";
        $db = new Database;
        $result = $db->query("DELETE FROM `cars` WHERE `cars`.`id` = $id");
        if ($result) {
            $response['success'] = 1;
            $response['msg'] = 'Xóa thông tin xe thành công';
            $response['data'] = '';
        } else {
            $response['success'] = 0;
            $response['msg'] = 'Có lỗi trong quá trình xóa';
            $response['data'] = '';
        }
    } else {
        $response['success'] = 0;
        $response['msg'] = 'Không có tham số id';
        $response['data'] = '';
    }
    echo json_encode($response);
?>