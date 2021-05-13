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
 
  $insert="INSERT INTO almacen(codigo, descripcion, precio) VALUES ('{$codigo}','{$descripcion}','{$precio}');";
  
 
  if($conexion->query($insert)===TRUE){
   
   
   $resultado = $conexion->query("SELECT * FROM almacen WHERE codigo = '{$codigo}'");
   //$resultado=mysqli_query($conexion, $consulta);
  
   if($registro=mysqli_fetch_array($resultado)){
    $json['producto'][]=$registro;
   }
   mysqli_close($conexion);
   echo json_encode($json);
   
  }else{
   $resulta["codigo"]=0;
   $resulta["descripcion"]="NO registra";
   $resulta["precio"]="NO registra";
   $json['producto'][]=$resulta;
   echo json_encode($json);
  }
 }else{
    $resulta["codigo"]=0;
    $resulta["descripcion"]="NO registra";
    $resulta["precio"]="NO registra";
    $json['producto'][]=$resulta;
    echo json_encode($json);
 }
?>