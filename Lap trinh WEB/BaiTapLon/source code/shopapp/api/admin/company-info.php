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
            case 'get':
                //region get
                try {
                    //Lấy 1 dòng đầu tiên của bảng company_info
                    $stmt = $conn->query('SELECT * FROM company_info LIMIT 1');
                    $stmt->execute();
                    if ($stmt->rowCount() >= 1) {
                        echo Response::getResponse(200, 'Lấy thông tin công ty thành công', $stmt->fetch());
                    } else {
                        echo Response::getResponse(404, 'Không có thông tin công ty', null);
                    }
                } catch (PDOException $ex) {
                    echo Response::getResponse(500, $ex->getMessage(), null);
                }

                break;
            //endregion
            case 'change':
                //region change
                if (isset($body->name)
                    && isset($body->email)
                    && isset($body->address)
                    && isset($body->hotline)
                    && isset($body->website)
                    && isset($body->aboutContent)
                ) {
                    $name = $body->name;
                    $email = $body->email;
                    $address = $body->address;
                    $aboutContent = $body->aboutContent;
                    $hotline = $body->hotline;
                    $website = $body->website;
                    try {
                        //Lấy 1 dòng đầu tiên của bảng company_info
                        $stmt = $conn->query('SELECT * FROM company_info LIMIT 1');
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            $row = $stmt->fetch();
                            $id = $row['id'];
                            //Thực hiện sửa thông tin
                            $stmt = $conn->query('UPDATE `company_info` SET `name` = :name, `about_content` = :aboutContent, `address` = :address, `email` = :email, `website` = :website, `hotline` = :hotline, `updated_at` = CURRENT_TIMESTAMP WHERE `company_info`.`id` = :id');
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':aboutContent', $aboutContent, PDO::PARAM_STR);
                            $stmt->bindParam(':address', $address, PDO::PARAM_STR);
                            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
                            $stmt->bindParam(':hotline', $hotline, PDO::PARAM_STR);
                            $stmt->bindParam(':website', $website, PDO::PARAM_STR);
                            $stmt->bindParam(':id', $id, PDO::PARAM_STR);
                            $stmt->execute();
                        } else {
                            // Tạo mới
                            $stmt = $conn->query('INSERT INTO `company_info` (`id`, `name`, `about_content`, `address`, `email`, `website`, `hotline`, `created_at`, `updated_at`) VALUES (NULL, :name, :aboutContent, :address, :email, :website, :hotline, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)');
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':aboutContent', $aboutContent, PDO::PARAM_STR);
                            $stmt->bindParam(':address', $address, PDO::PARAM_STR);
                            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
                            $stmt->bindParam(':hotline', $hotline, PDO::PARAM_STR);
                            $stmt->bindParam(':website', $website, PDO::PARAM_STR);
                            $stmt->execute();
                        }
                        echo Response::getResponse(200, 'Sửa thông tin công ty thành công', null);
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




