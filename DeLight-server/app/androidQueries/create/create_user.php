<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$response = array();

if (isset($_POST["login"])&&isset($_POST["password"])){

	$db = new DB_CONNECT();
	
	$login = $_POST["login"];
	$password = $_POST["password"];

	$result = create($login, $password, $db);

	if ($result) {

		$result = $db->getConnection()->query("SELECT *FROM users WHERE login = '$login'");

		if ($result->num_rows > 0) {

			$result = $result->fetch_array();

			$response["success"] = 1;
			$response["message"] = "user successfully created";
			
		} else {
			$response["success"] = 2;
			$response["message"] = "user not created";
		}
	} else {
		$response["success"] = 3;
		$response["message"] = "user already exists" ;
	}
} else {
	$response["success"] = 0;
	$response["message"] = "required field(s) is missing";
}

echo json_encode($response);

function passwordHash($password, $salt = null, $iterations = 10){
	$salt || $salt = uniqid();
	$hash = md5(md5($password . md5(sha1($salt))));

	for ($i = 0; $i < $iterations; ++$i) {
		$hash = md5(md5(sha1($hash)));
	}

	return array('hash' => $hash, 'salt' => $salt);
}

function getSalt($login, $db) {
	$query = "select salt from users where login = '$login'";

	$result = $db->getConnection()->query($query);
	$row = $result->fetch_array();

	if (!$row) {
		return false;
	}

	return true;
}

function create($login, $password, $db) {
	$user_exists = getSalt($login, $db);

	if ($user_exists) {
		return false;
	}

	$hashes = passwordHash($password);

	$password = $hashes["hash"];
	$salt = $hashes["salt"];

	$query = "insert into users (login, password, salt)
	values ('$login', '$password', '$salt')";

	$db->getConnection()->query($query);

	return true;
}

?>