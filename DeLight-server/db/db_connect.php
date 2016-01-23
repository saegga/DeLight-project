<?php

//класс для подключения к БД

class DB_CONNECT {
 
    var $con;

    function __construct() {
        $this->connect();
    }
 
    function __destruct() {
        $this->con->close();//закрытие соединения, срабатывает автоматически
    }

    function getConnection(){
        return $this->con;//получить соединение
    }
 
 //само подключение
    function connect() {
        require_once 'db_config.php';//подключение файла db_config.php
 //все эти константы берутся из db_config.php
        $this->con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

        if ($this->con->connect_error){
            die('Connect Error (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error);
        }

        $this->con->query("SET NAMES utf8");
        
        return $this->con;
    }
}
?>