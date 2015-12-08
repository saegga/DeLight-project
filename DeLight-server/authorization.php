<?php
session_start();
header('Content-type: text/html; charset=utf-8');

require 'entity/Auth.php';

$login = $_POST["login"];
$password = $_POST["password"];

$auth = new Auth($login, $password);

if ($_POST["from"] == "login"){
	if ($auth->authorize()){
		echo "success";
	} else {

	}

} else if ($_POST["from"] == "register"){
	$auth->create();
}

?>