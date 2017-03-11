<?php
include './include/DbHandler.php';
$db = new DbHandler();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['mobileNumber'])) {
 
    // receiving the post params
    $mobileNumber = $_POST['mobileNumber'];
	$message = $_POST['message'];
 
    // get the user by email and password
    $user = $db->getUserByMobile($mobileNumber);
 
    if ($user != false) {
        // use is found
		$db->insertIntoTable($message, $mobileNumber);
        $response["error"] = FALSE;
		$response["message"]="User found. Your OTP is: ";
		$response["profile"] = $user;
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["message"] = "No user with the mobile number found. Register first!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["message"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>