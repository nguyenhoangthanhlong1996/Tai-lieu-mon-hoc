<?php

require_once 'ConnectDatabase.php';

class ProductDAO
{
    private $conn;


    public function __construct()
    {
        $this->conn = new ConnectDatabase();
    }

    public function getListNewProducts($numLimit)
    {
        $stmt = $this->conn->query("SELECT p.id, p.name, p.price, p.cover_image, p.created_at, p.updated_at FROM products p ORDER BY p.created_at DESC LIMIT $numLimit");
        $stmt->execute();
        return $stmt->fetchAll();
    }

    public function getListBestSellerProducts($numLimit)
    {
        $stmt = $this->conn->query("SELECT p.id, p.name, p.price, p.cover_image, p.created_at, p.updated_at FROM products p ORDER BY p.created_at ASC LIMIT $numLimit");
        $stmt->execute();
        return $stmt->fetchAll();
    }

    public function getListProductByCategoryId($categoryId)
    {
        try {
            $stmt = $this->conn->query("SELECT p.id, p.name, p.price, p.cover_image, p.created_at, p.updated_at FROM products p WHERE p.category_id = $categoryId ORDER BY p.created_at DESC");
            $stmt->execute();
            return $stmt->fetchAll();
        } catch (PDOException $ex) {
            return null;
        }

    }

    public function getProductById($id)
    {
        try {
            $stmt = $this->conn->query("SELECT p.*, u.name as unit_name FROM products p, units u WHERE p.unit_id = u.id AND p.id = $id");
            $stmt->execute();
            return $stmt->fetch();
        } catch (PDOException $ex) {
            return null;
        }
    }

    public function getListRelatedProducts($productId, $numLimit)
    {
        try {
            $stmt = $this->conn->query("SELECT * FROM products WHERE category_id = (SELECT category_id FROM products WHERE id = $productId) AND id != $productId LIMIT $numLimit");
            $stmt->execute();
            return $stmt->fetchAll();
        } catch (PDOException $ex) {
            return null;
        }
    }

    public function getListProductsBySearchKey($searchKey) {
        try {
            $stmt = $this->conn->query("SELECT p.*, u.name as unit_name FROM products p INNER JOIN units u ON p.unit_id = u.id INNER JOIN categories c ON p.category_id = c.id WHERE LOWER(p.name) LIKE LOWER('%$searchKey%') OR LOWER(p.description) LIKE LOWER('%$searchKey%') OR LOWER(p.posts) LIKE LOWER('%$searchKey%') OR LOWER(p.tags) LIKE LOWER('%$searchKey%') OR LOWER(c.name) LIKE LOWER('%$searchKey%') OR LOWER(u.name) LIKE LOWER('%$searchKey%')");
            $stmt->execute();
            return $stmt->fetchAll();
        } catch (PDOException $ex) {
            return null;
        }
    }
}
