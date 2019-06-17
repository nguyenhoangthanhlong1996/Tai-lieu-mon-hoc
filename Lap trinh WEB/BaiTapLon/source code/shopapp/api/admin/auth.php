<?php
require '../../ConnectDatabase.php';
require '../../utils/Response.php';
require '../../utils/JWT.php';

if (isset($_REQUEST['action'])) {
    $action = $_REQUEST['action'];
    $body = json_decode(file_get_contents('php://input'));
    $conn = new ConnectDatabase();
    switch ($action) {
        case 'login':
            //region login
            if (isset($body->username) && isset($body->password)) {
                $username = $body->username;
                $password = md5($body->password);
                try {
                    $stmt = $conn->query('select * from users where username = :username and password = :password and role_id = 1');
                    $stmt->bindParam(':username',$username,PDO::PARAM_STR);
                    $stmt->bindParam(':password',$password,PDO::PARAM_STR);
                    $stmt->execute();
                    if ($stmt->rowCount() >=1) {
                        $token = JWT::encode(array("username" => $username), JWT::$SECRET_KEY);
                        echo Response::getResponse(200, "Đăng nhập thành công", array("token"=>$token));
                    } else {
                        echo Response::getResponse(401, "Sai thông tin đăng nhập", null);
                    }
                } catch (PDOException $ex) {
                    echo Response::getResponse(500, $ex->getMessage(), null);
                }
            } else {
                echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
            }
            break;
            //endregion
        case 'info':
            //region info
            $username = JWT::verifyToken();
            if ($username) {
                try {
                    $stmt = $conn->query('select username, full_name, email, phone, address, avatar from users where username = :username');
                    $stmt->bindParam(':username',$username,PDO::PARAM_STR);
                    $stmt->execute();
                    echo Response::getResponse(200, 'Lấy thông tin tài khoản thành công', $stmt->fetch());
                }  catch (PDOException $ex) {
                    echo Response::getResponse(500, $ex->getMessage(), null);
                }
            } else {
                echo Response::getResponse(401, 'Lỗi xác thực', null);
            }
            break;
            //endregion
        default:
            //region default
            echo Response::getResponse(404, null, null);
            break;
            //endregion
    }
} else {
    echo Response::getResponse(404, null, null);
}




