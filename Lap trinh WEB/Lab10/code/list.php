<?php
    require "Database.php";
    $db = new Database;
    $cars = $db->query("select * from cars");
    $response = array();
    $response['success'] = 1; 
    while($row = mysqli_fetch_assoc($cars)) {
        $response['data'][] = $row;
    }
    echo json_encode($response);
?>