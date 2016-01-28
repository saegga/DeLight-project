<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

$table = $_POST["table"];
$user_id = $_POST["user_id"];

$user_result = $db->getConnection()->query("SELECT * FROM $users WHERE user_id = '$user_id'");

$user_result = $userResult->fetch_array();

if ($user_result["role"] == "UNASSIGNED"){
    $access = "public";
} else {
    $access = "private";
}

$result = $db->getConnection()->query("SELECT * FROM $table WHERE access = '$access'");

if (!empty($result)) {
    $response["events"] = array();

    if ($result->num_rows > 0) {
        $response["success"] = 1;

        for ($i = 0; $i < $result->num_rows; $i++){
            $event_row = $result->fetch_array();

            $event["event_id"] = $event_row["event_id"];
            $event["start_time"] = $event_row["start_time"];
            $event["end_time"] = $event_row["end_time"];
            $event["place_id"] = $event_row["place_id"];
            $event["date"] = $event_row["date"];

            if ($table == "current_trainings"){
                $place_id = $event_row["place_id"];
                $result_place = $db->getConnection()->query("SELECT * FROM places WHERE place_id = '$place_id'");
                $result_place = $result_place->fetch_array();
                $extra = $result_place["name"];
            } else{
                $extra = $event_row["name"];
            }

            $event["extra"] = $extra;

            $response["events"][$i] = $event;
        }
    } else {
        $response["success"] = 0;
        $response["message"] = "Empty result";
    }
} else {
   $response["success"] = 0;
   $response["message"] = "mysql error";
}

echo json_encode($response);

?>