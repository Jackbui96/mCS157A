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
    
    $response = array('error' => false);
    
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        
        // Getting prams
        if (isset($_POST['userAccount']) 
		and isset($_POST['userPassword'])) {
            
            $userAccount  = $_POST['userAccount'];
            $userPassword = $_POST['userPassword'];
            
            $sql = 'SELECT * FROM Users';
            $retval = mysqli_query($conn,$sql);
			
            if (!$retval) {
                die('Could not get data: ' . mysql_error());
            }
            
            while ($row = mysqli_fetch_assoc($retval)) {
				
				$userAccountDB  = $row['userAccount'];
				$userPasswordDB = $row['userPassword'];
				
				if( $userAccount == $userAccountDB
				and $userPassword == $userPasswordDB){
					$response['error'] = false;
					$response['uid'] = $row['uid'];
					echo json_encode($response);
				}
				
            }
            
			mysqli_free_result($retval);
                
        } else {
            $response['error'] = true;
			echo json_encode($response);
        }
    } else {
        $response['error'] = true;
		echo json_encode($response);
    }
    
    $conn->close();
}

?> 