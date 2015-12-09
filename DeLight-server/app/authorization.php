<?php

session_start();

require 'entity/Auth.php';

$login = $_POST["login"];
$password = $_POST["password"];

if ($_POST["from"] == "login"){
	$auth = new Auth($login, $password);
	if ($auth->authorize(true)){
		echo true;
	} else {
		echo false;
	}

} else if ($_POST["from"] == "register"){
	$passwordConfirm = $_POST["passwordConfirm"];
	if ($passwordConfirm == $password){
		$auth = new Auth($login, $password);
		if ($auth->create()){
			echo true;
		} else {
			echo false;
		}
	}
}

?>