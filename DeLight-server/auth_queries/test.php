<?php
 
require '../db_connect.php';

$db = new DB_CONNECT();
	
$result = $db->getConnection()->query("SELECT *FROM users WHERE login = 'scaredChatsky'");

var_dump($result);

$result = $result->fetch_array();

echo json_encode($result);

?>