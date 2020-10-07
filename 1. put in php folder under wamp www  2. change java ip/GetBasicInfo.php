<?php require_once('Connections/connDB.php'); ?>
<?php
$response = array();

$input = file_get_contents("php://input");
$jsonObj = json_decode($input, true);
$username = $jsonObj['username'];
$requestType = $jsonObj['type'];

$query_rsUser = sprintf("SELECT * FROM customer WHERE username = '%s'",$username);

$rsUser = mysqli_query($connDB, $query_rsUser);
$userfound = mysqli_num_rows($rsUser);

if($userfound >= 1){
    $userInfo= mysqli_fetch_assoc($rsUser);
    $response["type"]= $requestType;
    $response["firstname"] = $row_rsUser['firstname'];
    $response["faculty"] = $row_rsUser['faculty'];
    $response["email"] = $row_rsUser['email'];
    $response["status"] = "OK";
}  
else{
	$response["type"] = $requestType;
    $response["status"] = "NOK";
}
    


echo json_encode($response);

mysqli_close($connDB);
?>