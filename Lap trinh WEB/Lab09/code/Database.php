<?php
class Database{
    public $host = "localhost";
    public $user = "root";
    public $pass = "";
    public $db = "examples";
    public $conn;
  
    public function __construct() {
        $this->db_connect();
    }
  
    private function db_connect(){
        //connection to the database
        $this->conn = mysqli_connect($this->host, $this->user, $this->pass) or die("Unable to connect to MySQL");
        $selected = mysqli_select_db($this->conn, $this->db) or die("Could not select examples");
    }
  
    public function query($sql) {
        $result = mysqli_query($this->conn, $sql);
        return $result;
    }
} 
?>