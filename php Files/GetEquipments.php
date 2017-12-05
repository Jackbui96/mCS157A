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

        if (isset($_POST['type'])
		and isset ($_POST['class'])) {

            $type = $_POST['type'];
			$class = $_POST['class'];

			$sql         = "SELECT * FROM `$type` WHERE `Requirement` LIKE '%$class%' ";
			$retval      = mysqli_query($conn, $sql);

            if (!$retval) {
                die('Could not get data: ' . mysql_error());
            }

            while ($row = mysqli_fetch_assoc($retval)) {

				$response[] = $row;
            }
			echo json_encode($response);

            mysqli_free_result($retval);


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
