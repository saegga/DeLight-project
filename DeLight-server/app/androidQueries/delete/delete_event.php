<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

if (isset($_POST["table"]) && isset($_POST["event_id"])) {

	$table = $_POST["table"];
	$event_id = $_POST["event_id"];

	$db->getConnection()->query("DELETE FROM $table WHERE event_id = '$event_id'");

	$response["success"] = 1;
	$response["message"] = "event successfully deleted";

} else {
	$response["success"] = 0;
	$response["message"] = "require field(S) is missing";
}

echo json_encode($response);

?>