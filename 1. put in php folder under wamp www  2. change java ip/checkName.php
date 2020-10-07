<?php require_once('Connections/connDB.php'); ?>
<?php
$response = array();

$input = file_get_contents("php://input");
$jsonObj = json_decode($input, true);
$name = $jsonObj['firstname'];
$requestType = $jsonObj['type'];

$query_rsUser = sprintf("SELECT * FROM customer WHERE firstname = '%s'",$name);

$rsUser = mysqli_query($connDB, $query_rsUser);
$userfound = mysqli_num_rows($rsUser);
$i=0;
if($userfound >= 1){
	while ($row_rsUser = mysqli_fetch_assoc($rsUser)){
	$response["type"]= $requestType;
	$response["id"] = (int)$row_rsUser['id'];
	$response["username"][$i] = $row_rsUser['username'];
	$response["firstname"][$i] = $row_rsUser['firstname'];
	$response["faculty"][$i] = $row_rsUser['faculty'];
    $response["email"][$i] = $row_rsUser['email'];
	$response["status"] = "OK";
	$i++;
	}


}  
else{
    $response["type"] = $requestType;
    $response["status"] = "NOK";
}

echo json_encode($response);

mysqli_close($connDB);
?>