<?php require_once('Connections/connDB.php'); ?>
<?php
$response = array();

$input = file_get_contents("php://input");
$jsonObj = json_decode($input, true);
$myuser = $jsonObj['user1']; 		// requestor
$youruser = $jsonObj['user2'];		// requested
$requestType = $jsonObj['type'];
$rsInsertFriend = 0 ;

	$query_rsInsertFriend = sprintf("INSERT INTO add_friends (Requestor, Requested) VALUES ('%s','%s')", $myuser, $youruser);
	$rsInsertFriend = mysqli_query($connDB, $query_rsInsertFriend);
	$rsID1 = mysqli_insert_id($connDB);

	$response["type"] = $requestType;
	$response["id1"] = $rsID1;
	$response["user1"] = $myuser;
	$response["user2"] = $youruser;

	if($rsInsertFriend != 0)
	{
		
		$response["status"] = "OK";
	}
	else
	{
		$response["status"] = "NOK";
	}
	

echo json_encode($response);

mysqli_close($connDB);
?>