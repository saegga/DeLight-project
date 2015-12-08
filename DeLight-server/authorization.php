<?php
session_start();
header('Content-type: text/html; charset=utf-8');

require 'entity/Auth.php';

$login = $_POST["login"];
$password = $_POST["password"];

$auth = new Auth($login, $password);

if ($_POST["from"] == "login"){
	if ($auth->authorize(true)){
		echo true;
	} else {
		echo false;
	}

} else if ($_POST["from"] == "register"){
	if ($auth->create()){
		echo true;
	}
}

?>