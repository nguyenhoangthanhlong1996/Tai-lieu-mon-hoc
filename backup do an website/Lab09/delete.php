<?php 
    require "Database.php";
    $db = new Database;

    if (isset($_GET['id'])) {
        $id = $_GET['id'];
        if (!empty($id)) { 
            $sql = "DELETE FROM `cars` WHERE `cars`.`id` = $id";
            if (!$db->query($sql)) {
                echo "lỗi";
            }
        }
    }
    header('Location: index.php');
?>