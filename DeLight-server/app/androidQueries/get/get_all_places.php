<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

$result = $db->getConnection()->query("SELECT * FROM places");

if (!empty($result)) {
    $response["places"] = array();

    if ($result->num_rows > 0) {
        $response["success"] = 1;

        for ($i = 0; $i < $result->num_rows; $i++){
            $place_row = $result->fetch_array();

            $place["place_id"] = $place_row["place_id"];
            $place["name"] = $place_row["name"];

            $response["places"][$i] = $place;
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