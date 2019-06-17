<?php

require_once 'ConnectDatabase.php';

class CompanyInfoDAO
{
    private $conn;


    public function __construct()
    {
        $this->conn = new ConnectDatabase();
    }

    public function getCompanyInfo()
    {
        $stmt = $this->conn->query('SELECT * FROM company_info');
        $stmt->execute();
        return $stmt->fetch();
    }
}
