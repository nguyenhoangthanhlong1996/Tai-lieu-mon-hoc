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
                if (isset($body->categoryId)
                    && isset($body->unitId)
                    && isset($body->name)
                    && isset($body->price)
                    && isset($body->quantityRemaining)
                    && isset($body->outOfStockFlag)
                    && isset($body->description)
                    && isset($body->posts)
                    && isset($body->tags)
                    && isset($body->coverImage)
                ) {
                    $categoryId = $body->categoryId;
                    $unitId = $body->unitId;
                    $name = $body->name;
                    $price = $body->price;
                    $quantityRemaining = $body->quantityRemaining;
                    $outOfStockFlag = $body->outOfStockFlag;
                    $description = $body->description;
                    $posts = $body->posts;
                    $tags = $body->tags;
                    $coverImage = $body->coverImage;

                    try {
                        //Kiểm tra xem tên sản phẩm này đã tồn tại hay chưa
                        $stmt = $conn->query('select * from products where name = :name');
                        $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(500, 'Tên sản phẩm này đã tồn tại', null);
                        } else {
                            $stmt = $conn->query('INSERT INTO `products` (`id`, `category_id`, `unit_id`, `name`, `price`, `quantity_remaining`, `out_of_stock_flag`, `description`, `posts`, `tags`, `cover_image`, `created_at`, `updated_at`) VALUES (NULL, :categoryId, :unitId, :name, :price, :quantityRemaining, :outOfStockFlag, :description, :posts, :tags, :coverImage, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)');
                            $stmt->bindParam(':categoryId', $categoryId, PDO::PARAM_INT);
                            $stmt->bindParam(':unitId', $unitId, PDO::PARAM_INT);
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':price', $price, PDO::PARAM_INT);
                            $stmt->bindParam(':quantityRemaining', $quantityRemaining, PDO::PARAM_INT);
                            $stmt->bindParam(':outOfStockFlag', $outOfStockFlag, PDO::PARAM_BOOL);
                            $stmt->bindParam(':description', $description, PDO::PARAM_STR);
                            $stmt->bindParam(':posts', $posts, PDO::PARAM_STR);
                            $stmt->bindParam(':tags', $tags, PDO::PARAM_STR);
                            $stmt->bindParam(':coverImage', $coverImage, PDO::PARAM_STR);
                            $stmt->execute();
                            echo Response::getResponse(200, 'Tạo sản phẩm thành công', null);
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
                    //Lấy về danh sách tất cả sản phẩm
                    $stmt = $conn->query('SELECT p.id, c.name as category_name, p.name, p.price, p.out_of_stock_flag, p.created_at, p.updated_at  FROM products p, categories c WHERE p.category_id = c.id');
                    $stmt->execute();
                    echo Response::getResponse(200, 'Lấy danh sách sản phẩm thành công', $stmt->fetchAll());
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
                        //Lấy thông tin theo id sản phẩm
                        $stmt = $conn->query('SELECT p.*, c.name as category_name, u.name as unit_name FROM products p, categories c, units u WHERE p.category_id = c.id AND p.unit_id = u.id AND p.id = :id');
                        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1) {
                            echo Response::getResponse(200, 'Lấy thông tin sản phẩm thành công', $stmt->fetch());
                        } else {
                            echo Response::getResponse(404, 'Không tìm thấy thông tin sản phẩm', null);
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
                    && isset($body->categoryId)
                    && isset($body->unitId)
                    && isset($body->name)
                    && isset($body->price)
                    && isset($body->quantityRemaining)
                    && isset($body->outOfStockFlag)
                    && isset($body->description)
                    && isset($body->posts)
                    && isset($body->tags)
                    && isset($body->coverImage)
                ) {
                    $id = $body->id;
                    $categoryId = $body->categoryId;
                    $unitId = $body->unitId;
                    $name = $body->name;
                    $price = $body->price;
                    $quantityRemaining = $body->quantityRemaining;
                    $outOfStockFlag = $body->outOfStockFlag;
                    $description = $body->description;
                    $posts = $body->posts;
                    $tags = $body->tags;
                    $coverImage = $body->coverImage;

                    try {
                        //Kiểm tra xem tên sản phẩm này đã tồn tại hay chưa
                        $stmt = $conn->query('select * from products where name = :name');
                        $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                        $stmt->execute();
                        if ($stmt->rowCount() >= 1 && $stmt->fetch()['id'] != $id) {
                            echo Response::getResponse(500, 'Tên sản phẩm này đã tồn tại', null);
                        } else {
                            $stmt = $conn->query('UPDATE `products` SET `category_id` = :categoryId, `unit_id` = :unitId, `name` = :name, `price` = :price, `quantity_remaining` = :quantityRemaining, `out_of_stock_flag` = :outOfStockFlag, `description` = :description, `posts` = :posts, `tags` = :tags, `cover_image` = :coverImage, `updated_at` = CURRENT_TIMESTAMP WHERE `products`.`id` = :id');
                            $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                            $stmt->bindParam(':categoryId', $categoryId, PDO::PARAM_INT);
                            $stmt->bindParam(':unitId', $unitId, PDO::PARAM_INT);
                            $stmt->bindParam(':name', $name, PDO::PARAM_STR);
                            $stmt->bindParam(':price', $price, PDO::PARAM_INT);
                            $stmt->bindParam(':quantityRemaining', $quantityRemaining, PDO::PARAM_INT);
                            $stmt->bindParam(':outOfStockFlag', $outOfStockFlag, PDO::PARAM_BOOL);
                            $stmt->bindParam(':description', $description, PDO::PARAM_STR);
                            $stmt->bindParam(':posts', $posts, PDO::PARAM_STR);
                            $stmt->bindParam(':tags', $tags, PDO::PARAM_STR);
                            $stmt->bindParam(':coverImage', $coverImage, PDO::PARAM_STR);
                            $stmt->execute();
                            echo Response::getResponse(200, 'Sửa sản phẩm thành công', null);
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
                        //Xóa sản phẩm
                        $stmt = $conn->query('DELETE FROM `products` WHERE `products`.`id` = :id');
                        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
                        $stmt->execute();
                        echo Response::getResponse(200, 'Xóa sản phẩm thành công', null);
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




