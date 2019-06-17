<?php
    $response = array();
    if (isset($_REQUEST['id'])) {
        $id = $_REQUEST['id'];
        require "Database.php";
        $db = new Database;
        $result = $db->query("SELECT * from cars WHERE id = $id");
        $car = mysqli_fetch_assoc($result);
        if (isset($car)) {
            $response['success'] = 1;
            $response['msg'] = 'Lấy thông tin xe thành công';
            $response['data'] = $car;
        } else {
            $response['success'] = 0;
            $response['msg'] = 'Không tìm thấy thông tin xe';
            $response['data'] = '';
        }
    } else {
        $response['success'] = 0;
        $response['msg'] = 'Không có tham số id';
        $response['data'] = '';
    }
    echo json_encode($response);
?>