<?php
 
$response = array();
 
require '../db_connect.php';
 
$db = new DB_CONNECT();
 
if (isset($_POST["training_id"])) {
    $training_id = $_POST['training_id'];
 
    $result = $db->getConnection()->query("SELECT *FROM trainings WHERE training_id = $training_id");
 
    if (!empty($result)) {
        if (mysqli_num_rows($result) > 0) {
 
            $result = mysqli_fetch_array($result);
            
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
        $response["message"] = "Training not found";
 
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    echo json_encode($response);
}
?>