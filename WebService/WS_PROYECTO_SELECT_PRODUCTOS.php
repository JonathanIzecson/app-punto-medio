<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();
    
    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);
    //Ejecutar sentencia sql        
    $consulta = "CALL SELECT_PRODUCTOS();";
    $resultado = $conexion->query($consulta);

        while($registro=mysqli_fetch_array($resultado)){
            $json['datos'][]=$registro;
    }
    echo json_encode($json);
?>
