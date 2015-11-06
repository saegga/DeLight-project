	
	<?php

		if(isset($_POST["key"])){
	//$key="123";
			$response = array();
			$key = $_POST["key"];
			$arrKey = array("123","589","748");
			
			foreach ($arrKey as $value) {
				 $comp = strcmp($value, $key);
				if($comp == 0){
						$result = array("status" => "success", "message" => "key find");
						echo json_encode ($result);
						return;
				}
			}
			$result = array("status" => "success", "message" => "key not found");
			echo json_encode($result);
		}
	?>