<?php
 
class DB_CONNECT {
 
    var $con;

    function __construct() {
        $this->connect();
    }
 
    function __destruct() {
        $this->con->close();
    }
 
    function connect() {
        require 'db_config.php';
 
        $this->con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

        if ($this->con->connect_error){
            die('Connect Error (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error);
        }
        
        return $this->con;
    }
}
?>