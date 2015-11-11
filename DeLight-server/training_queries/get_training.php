<?php
 
require '../db_connect.php';
 
$db = new DB_CONNECT();
 
if (isset($_POST["training_id"])) {

    $response = array();

    $training_id = $_POST['training_id'];
 
    $result = $db->getConnection()->query("SELECT *FROM trainings WHERE training_id = $training_id");
 
    if (!empty($result)) {
        if ($result->num_rows > 0) {
 
            $result = $result->fetch_array();
            
            $training = array();
            $training["training_id"] = $result["training_id"];
            $training["name"] = $result["name"];
            $training["owner_login"] = $result["owner_login"];
            $training["time"] = $result["time"];
            $training["dayOfWeek"] = $result["dayOfWeek"];
            $training["agenda"] = $result["agenda"];
            $response["success"] = 1;
 
            $response["training"] = array();
 
            array_push($response["training"], $training);
 
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            $response["message"] = "Training not found";
 
            echo json_encode($response);
        }
    } else {
        $response["success"] = 0;
        $response["message"] = "Empty result";
 
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    echo json_encode($response);
}
?>