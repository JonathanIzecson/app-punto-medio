<?php

$usuario = $_REQUEST['usu'];
$email = $_REQUEST['email'];

$conexion = new PDO ("mysql:host=localhost;dbname=webserviceandroid","root","12345678");
$resulset = $conexion->query("select * from usuario where nombre = '$usuario' and email = '$email'");

$datos = array();
$respuesta = array();
foreach($resulset as $row){
    $datos[] = $row;
}
if(empty($datos)){
    
    $respuesta = ["estado" => "error"];
    echo json_encode($respuesta);

}else{
    $respuesta = ["estado" => "exito"];
    $respuesta += $datos;//["estado" => "exito"];
    echo json_encode($respuesta);
    
}
?>