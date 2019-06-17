<?php

require_once 'ConnectDatabase.php';

class CategoryDAO
{
    private $conn;


    public function __construct()
    {
        $this->conn = new ConnectDatabase();
    }

    public function getListCategories()
    {
        $stmt = $this->conn->query('select * from categories');
        $stmt->execute();
        return $stmt->fetchAll();
    }

    public function getCategoryById($id)
    {
        try {
            $stmt = $this->conn->query("select * from categories where id = $id");
            $stmt->execute();
            return $stmt->fetch();
        } catch (PDOException $ex) {
            return null;
        }
    }
}
