<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios
if(isset($_GET["username"]) && isset($_GET["password"])){
    $username = $_GET["username"];
    $pass = $_GET["password"];    
    
    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);


    $sql = "SELECT * FROM USUARIOS WHERE US_USERNAME = '{$username}' && US_PASSWORD = MD5('{$pass}');";

    //Ejecutar sentencia sql
    $conexion->query($sql);

    //Determinar si se realizo la accion
    $row_affected = $conexion->affected_rows;

     //Definir accion para cada caso
     if($row_affected == 1){

           $resultadoAcceso ["acceso"]="permitido";
            $json['respuesta'][]=$resultadoAcceso;
            echo json_encode($json);
    }else{
        //Usuario y/o contraseña incorrectos
        $resultadoAcceso ["acceso"]="denegado";
        $json['respuesta'][] = $resultadoAcceso;
        echo json_encode($json);
    }
}else{
    //Error si algun campo no se ingresó
    $resultado ["resultado"]="vacio";
    $json['respuesta'][] = $resultado;
    echo json_encode($json);
    }

?>