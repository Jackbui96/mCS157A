<?php
$servername = "localhost";
$username   = "id3335250_admin";
$password   = "NguyenBui!996";
$dbname     = "id3335250_cs157a";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error)
    die("Connection failed: " . $conn->connect_error);
else {
    
    $response = array(
        'error' => false
    );

	$sql    = "SELECT * FROM Classes";
	$retval = mysqli_query($conn, $sql);
    
	if (!$retval) {
		die('Could not get data: ' . mysql_error());
	}
	
	while ($row = mysqli_fetch_assoc($retval))
		$response[] = $row;
	
	echo json_encode($response);
	mysqli_free_result($retval);
    
    $conn->close();
}

?> 