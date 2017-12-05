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

        if (isset($_POST['uid'])) {

            $uid    = $_POST['uid'];
            $sql    = "SELECT * FROM Characters WHERE Owned_By_id = '$uid'";
            $retval = mysqli_query($conn, $sql);

            if (!$retval) {
                die('Could not get data: ' . mysql_error());
            }

            while ($row = mysqli_fetch_assoc($retval))
                $response[] = $row;

            $response['msg'] = "Recall Completed!!!";

            echo json_encode($response);
            mysqli_free_result($retval);

        } else {
            $response['error'] = true;
            $response['msg']   = "Fail to Fetch Params...
            \nNeed to Check for Internet";
            echo json_encode($response);
        }

    } else {
        $response['error'] = true;
        $response['msg']   = "Oops, Something is wrong...";
        echo json_encode($response);
    }

    $conn->close();
}

?>
