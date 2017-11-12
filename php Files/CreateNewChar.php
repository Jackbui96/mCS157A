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
    
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        
        if (isset ($_POST['uid'])
		and isset ($_POST['name'])
		and isset ($_POST['class'])
		and isset ($_POST['weapon'])
		and isset ($_POST['armor'])
		and isset ($_POST['accessory'])) {
            
            $uid = $_POST['uid'];
			$name = $_POST['name'];
			$class = $_POST['class'];
			$weapon = $_POST['weapon'];
			$armor = $_POST['armor'];
			$accessory = $_POST['accessory'];
			
			$sql         = "INSERT INTO Characters (Owned_By_id, Character_Name, 
			Character_Class, Current_Weapon, Current_Armor, Current_Accessory)
			VALUES ('$uid', '$name', '$class', '$weapon', '$armor', '$accessory')";
			
			$resp = mysqli_query($conn, $sql);
            $reponse['error'] = false;
			
			echo json_encode($response);
        		
		
		} else {
            $response['error'] = true;
            $response['msg']   = "Fail to fetch params";
            echo json_encode($response);
        }
    
    } else {
        $response['error'] = true;
        echo json_encode($response);
    }
    
    $conn->close();
}

?> 