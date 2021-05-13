<?php

$hostname_localhost="localhost";
$database_localhost="bamepoliux";
$username_localhost="root";
$password_localhost="12345678";

$json=array();
 if(isset($_GET["codigo"])){
  $codigo=$_GET['codigo'];


  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
 
  $delete="DELETE FROM almacen WHERE CODIGO = '{$codigo}';";
  
  $conexion->query($delete);

  $afectada = $conexion->affected_rows;

  if($afectada > 0){
    $estado['estado'] = "exito";
    $json['respuesta'][] = $estado;
  }else if($afectada == 0){
    $estado['estado'] = "inexistente";
    $json['respuesta'][] = $estado;
  }else{
    $estado['estado'] = "error";
    $json['respuesta'][] = $estado;
  }
  echo json_encode($json);
  mysqli_close($conexion);
 }
 
?>