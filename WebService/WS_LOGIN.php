<?php

$WS_USER = $_GET['WS_USER'];
$WS_PASS = $_GET['WS_PASS'];

include "Connections/conexion78.php";

$sql = "INSERT INTO USUARIOS VALUES(NULL,'$WS_USER','$WS_PASS');";

if ($conexion78->query($sql) === TRUE) {
    $json = array(["estado" => "exito"]);
} else {
    $json = array(["estado" => "error"]);
}
echo json_encode($json);
$conexion78->close();
?>