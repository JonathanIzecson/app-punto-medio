<?php

$usuario = $_REQUEST['usu'];
$password = $_REQUEST['pas'];

$conexion = new PDO ("mysql:host=localhost;dbname=webserviceandroid","root","12345678");
$resulset = $conexion->query("select * from alumnos where usuario = '$usuario' and password = '$password'");

$datos = array();

foreach($resulset as $row){
    $datos[] = $row;
}
echo json_encode($datos);
?>