<?php

$usuario = $_REQUEST['usu'];
$password = $_REQUEST['pas'];

$conexion = new PDO ("mysql:host=localhost;dbname=webserviceandroid","root","12345678");
//$resulset = $conexion->query("insert into alumnos values('$usuario','$password')");

$url = "insert into alumnos values(':usu',':pas')";
$sentencia = $conexion->prepare($sql);
$sentencia->bindParam(":usu", $usuario  );
$sentencia->bindParam(":pas",  $password);
$resultado = $sentencia->execute();

if ($resultado != 1){
    //ocurrio un error al insertar
    $json = array(["estado" => "error"]);
}else{
    //ocurrio un error al insertar
    $json = array(["estado" => "exito"]);
}


/*$datos = array();

foreach($resulset as $row){
    $datos[] = $row;
}*/
echo json_encode($json);
echo $resultado;
?>