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
                if (isset($body->name)
                    && isset($body->note)
                ) {
                    $name = $body->name;
                    $note = $body->note;
                    try {
                        //Kiểm tra xem tên danh mục này đã tồn tại hay chưa
                        $stmt = $conn->query('select * from categories where name = :name');
                        $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(500, 'Tên danh mục này đã tồn tại', null);
                        } else {
                            $stmt = $conn->query('INSERT INTO `categories` (`id`, `name`, `note`, `created_at`, `updated_at`) VALUES (NULL, :name, :note, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)');
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':note', $note, PDO::PARAM_STR);
                            $stmt->execute();
                            echo Response::getResponse(200, 'Tạo danh mục thành công', null);
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
                    //Lấy về danh sách tất cả category
                    $stmt = $conn->query('select * from categories');
                    $stmt->execute();
                    echo Response::getResponse(200, 'Lấy danh sách danh mục thành công', $stmt->fetchAll());
                } catch (PDOException $ex) {
                    echo Response::getResponse(500, $ex->getMessage(), null);
                }
                break;
            //endregion
            case 'detail':
                //region detail
                if (isset($body->id)) {
                    $id = $body->id;
                    try {
                        //Lấy thông tin theo id danh mục
                        $stmt = $conn->query('select * from categories WHERE id = :id');
                        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(200, 'Lấy thông tin danh mục thành công', $stmt->fetch());
                        } else {
                            echo Response::getResponse(404, 'Không tìm thấy thông tin danh mục', null);
                        }
                    } catch (PDOException $ex) {
                        echo Response::getResponse(500, $ex->getMessage(), null);
                    }
                } else {
                    echo Response::getResponse(500, "Sai dữ liệu đầu vào", null);
                }
                break;
            //endregion;
            case 'update':
                //region update
                if (isset($body->id)
                    && isset($body->name)
                    && isset($body->note)
                ) {
                    $id = $body->id;
                    $name = $body->name;
                    $note = $body->note;
                    try {
                        //Kiểm tra xem tên danh mục này đã tồn tại hay chưa
                        $stmt = $conn->query('select * from categories where name = :name');
                        $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1 && $stmt->fetch()['id'] != $id) {
                            echo Response::getResponse(500, 'Tên danh mục này đã tồn tại', null);
                        } else {
                            $stmt = $conn->query('UPDATE `categories` SET `name` = :name, `note` = :note, `updated_at` = CURRENT_TIMESTAMP WHERE `categories`.`id` = :id');
                            $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':note', $note, PDO::PARAM_STR);
                            $stmt->execute();
                            echo Response::getResponse(200, 'Sửa danh mục thành công', null);
                        }
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
                if (isset($body->id)) {
                    $id = $body->id;
                    try {
                        //Xóa danh mục
                        $stmt = $conn->query('DELETE FROM `categories` WHERE `categories`.`id` = :id');
                        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                        $stmt->execute();
                        echo Response::getResponse(200, 'Xóa danh mục thành công', null);
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




