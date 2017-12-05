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

        // Getting prams
        if (isset($_POST['usrAccount'])
        and isset($_POST['usrPassword'])) {

            $userAccount  = $_POST['usrAccount'];
            $userPassword = $_POST['usrPassword'];

            $sql    = "SELECT * FROM Users WHERE userAccount = '" . $userAccount . "'";
            $retval = mysqli_query($conn, $sql);

            if (!$retval) {
                die('Could not get data: ' . mysql_error());
            } else if ($row = mysqli_fetch_assoc($retval) == 0) {

                $sql              = "INSERT INTO Users (userAccount, userPassword)
                    VALUES ('$userAccount', '$userPassword')";

                $insertRetval     = mysqli_query($conn, $sql);
                $reponse['error'] = false;
                $response['msg']  = "Account created!!!\nWelcome dear Adventure";
                echo json_encode($response);
                mysqli_free_result($insertRetval);
            } else {
                $response['error'] = true;
                $response['msg']   = "There is already an Adventure by that name";
                echo json_encode($response);
            }

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
