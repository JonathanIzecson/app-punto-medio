<?php
$hostname_localhost="localhost";
$database_localhost="bamepoliux";
$username_localhost="root";
$password_localhost="12345678";

$json=array();
 if(isset($_GET["codigo"]) && isset($_GET["descripcion"]) && isset($_GET["precio"])){
  $codigo=$_GET['codigo'];
  $descripcion=$_GET['descripcion'];
  $precio=$_GET['precio'];


  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
 
  $update="UPDATE almacen SET descripcion = '{$descripcion}' , precio = '{$precio}' WHERE CODIGO = '{$codigo}';";
  
  $conexion->query($update);
  $afectada = $conexion->affected_rows;

  if($afectada > 0){

    $estado['estado'] = "exito";
    $json['respuesta'][] = $estado;
  }else if($afectada == 0){
    $estado['estado'] = "error";
    $json['respuesta'][] = $estado;
  }
  echo json_encode($json);
  mysqli_close($conexion);
 }
 
?>