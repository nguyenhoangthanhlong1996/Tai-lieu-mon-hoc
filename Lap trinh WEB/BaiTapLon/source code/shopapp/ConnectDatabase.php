<?php

class ConnectDatabase
{
    private $host = 'localhost';
    private $username = 'root';
    private $password = '';
    private $dbname = 'shopapp';
    private $pdo;

    /**
     * ConnectDatabase constructor.
     */
    public function __construct()
    {
        try {
            $this->pdo = new PDO("mysql:host=$this->host;dbname=$this->dbname", $this->username, $this->password, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
            $this->pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
            $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }

    public function query($sql)  {
        return $this->pdo->prepare($sql);
    }

    public function excute($sql) {
        return $this->pdo->exec($sql);
    }
}

