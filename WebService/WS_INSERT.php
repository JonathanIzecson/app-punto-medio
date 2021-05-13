<?php

$WS_user = $_GET [ 'WS_user' ];

$WS_pass = $_GET [ 'WS_pass' ];


include "Connections/conexion78.php";

$sql = "INSERT INTO USUARIOS VALUES(null,'$WS_user','$WS_pass')";

if ($conexion78->query($sql) === 1) {
    $json = array(["estado" => "exito"]);
} else {
    $json = array(["estado" => "error"]);
}
echo json_encode($json);
$conexion78->close();
?>