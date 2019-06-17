<?php
require '../../ConnectDatabase.php';
require '../../utils/Response.php';
require '../../utils/JWT.php';

if (isset($_REQUEST['action'])) {
    $action = $_REQUEST['action'];
    $body = json_decode(file_get_contents('php://input'));
    $conn = new ConnectDatabase();
    if (JWT::verifyToken()) { //Đã đăng nhập
        switch ($action) {
            case 'create':
                //region create
                if (isset($body->username)
                    && isset($body->password)
                    && isset($body->name)
                    && isset($body->email)
                    && isset($body->phone)
                    && isset($body->address)
                    && isset($body->avatar)

                ) {
                    $username = $body->username;
                    $password = md5($body->password);
                    $name = $body->name;
                    $email = $body->email;
                    $phone = $body->phone;
                    $address = $body->address;
                    $avatar = $body->avatar;
                    try {
                        //Kiểm tra xem tên tài khoản đã tồn tại hay chưa
                        $stmt = $conn->query('select * from users where username = :username');
                        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(500, 'Tên tài khoản này đã tồn tại', null);
                        } else {
                            $stmt = $conn->query('INSERT INTO `users` (`username`, `password`, `full_name`, `email`, `phone`, `address`, `avatar`, `role_id`, `created_at`, `updated_at`) VALUES (:username, :password, :full_name, :email, :phone, :address, :avatar, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)');
                            $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                            $stmt->bindParam(':password', $password, PDO::PARAM_STR);
                            $stmt->bindParam(':full_name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
                            $stmt->bindParam(':phone', $phone, PDO::PARAM_STR);
                            $stmt->bindParam(':address', $address, PDO::PARAM_STR);
                            $stmt->bindParam(':avatar', $avatar, PDO::PARAM_STR);
                            $stmt->execute();
                            echo Response::getResponse(200, 'Tạo tài khoản thành công', null);
                        }
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
            //endregion
            case 'list':
                //region list
                try {
                    //Lấy về danh sách tất cả user
                    $stmt = $conn->query('select username, full_name, email, phone, created_at, updated_at from users where role_id != 1');
                    $stmt->execute();
                    echo Response::getResponse(200, 'Lấy danh sách tài khoản thành công', $stmt->fetchAll());
                } catch (PDOException $ex) {
                    echo Response::getResponse(500, $ex->getMessage(), null);
                }
                break;
            //endregion
            case 'detail':
                //region detail
                if (isset($body->username)) {
                    $username = $body->username;
                    try {
                        //Lấy thông tin theo tên tài khoản
                        $stmt = $conn->query('select username, full_name, email, phone, address, avatar, r.name as role_name, u.created_at, u.updated_at from users u, roles r WHERE u.role_id = r.id AND u.username = :username');
                        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(200, 'Lấy thông tin chi tiết tài khoản thành công', $stmt->fetch());
                        } else {
                            echo Response::getResponse(404, 'Không tìm thấy thông tin tài khoản', null);
                        }
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
            //endregion;
            case 'change-info':
                //region change-info
                if (isset($body->username)
                    && isset($body->name)
                    && isset($body->email)
                    && isset($body->phone)
                    && isset($body->address)
                    && isset($body->avatar)
                ) {
                    $username = $body->username;
                    $name = $body->name;
                    $email = $body->email;
                    $phone = $body->phone;
                    $address = $body->address;
                    $avatar = $body->avatar;
                    try {
                        //Thay đổi thông tin
                        $stmt = $conn->query('UPDATE `users` SET `full_name` = :name, `email` = :email, `phone` = :phone, `address` = :address, `avatar` = :avatar, `updated_at` = CURRENT_TIMESTAMP WHERE `users`.`username` = :username');
                        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                        $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                        $stmt->bindParam(':email', $email, PDO::PARAM_STR);
                        $stmt->bindParam(':phone', $phone, PDO::PARAM_STR);
                        $stmt->bindParam(':address', $address, PDO::PARAM_STR);
                        $stmt->bindParam(':avatar', $avatar, PDO::PARAM_STR);
                        $stmt->execute();
                        echo Response::getResponse(200, 'Sửa thông tin tài khoản thành công', null);
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
            //endregion
            case 'change-password':
                //region change-password
                if (isset($body->username)
                    && isset($body->password)
                ) {
                    $username = $body->username;
                    $password = md5($body->password);
                    try {
                        //Thay đổi mật khẩu
                        $stmt = $conn->query('UPDATE `users` SET `password` = :password, `updated_at` = CURRENT_TIMESTAMP WHERE `users`.`username` = :username');
                        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                        $stmt->bindParam(':password', $password, PDO::PARAM_STR);
                        $stmt->execute();
                        echo Response::getResponse(200, 'Đổi mật khẩu tài khoản thành công', null);
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
                //endregion
            case 'remove':
                //region remove
                if (isset($body->username)) {
                    $username = $body->username;
                    try {
                        //Xóa tài khoản
                        $stmt = $conn->query('DELETE FROM `users` WHERE `users`.`username` = :username');
                        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
                        $stmt->execute();
                        echo Response::getResponse(200, 'Xóa tài khoản thành công', null);
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
                //endregion
            default:
                //region default
                echo Response::getResponse(404, null, null);
                break;
            //endregion
        }
    } else { //chưa đăng nhập
        echo Response::getResponse(401, 'Lỗi Xác thực', null);
    }
} else {
    echo Response::getResponse(404, null, null);
}




