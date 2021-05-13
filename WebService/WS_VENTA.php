<?php

include "Connections/conexion78.php";


/*$sql = "INSERT INTO USUARIOS VALUES(1,'','')";
if ($conexion78->query($sql) === 1) {
    $json = array(["estado" => "exito"]);
} else {
    $json = array(["estado" => "error"]);
}*/

$sql = "select * from notas;";
$result = mysqli_query ( $conexion78,$sql );
echo $result;
$conexion78->close();
?>
