<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios

if(isset($_GET["nombre"]) && isset($_GET["apellido"]) && isset($_GET["sexo"]) 
    && isset($_GET["telefono"]) && isset($_GET["email"]) && isset($_GET["username"]) 
    && isset($_GET["password"])){
    

    $nombre = $_GET["nombre"];
    $apellido = $_GET["apellido"];
    $sexo = $_GET["sexo"];
    $telefono = $_GET["telefono"];
    $email = $_GET["email"];
    $username = $_GET["username"];
    $pass = $_GET["password"];

    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);

    //Definir sentencia sql
    $call = "CALL IN_USUARIOS(null,'{$nombre}','{$apellido}','{$sexo}','{$telefono}','{$email}','{$username}','{$pass}');";

    //Ejecutar sentencia sql
    $conexion->query($call);

    //Determinar si se realizo la accion
    $row_affected = $conexion->affected_rows;

    //Definir accion para cada caso
    if($row_affected == 1){
        $resultado ["resultado"]="exito";
        $json['respuesta'][] = $resultado;
        echo json_encode($json);
    }else{
        //Error si no se pudo insertar
        $resultado ["resultado"]="error";
        $json['respuesta'][] = $resultado;
        echo json_encode($json);
    }
}else{
    //Error si algun campo no se ingresó
    $resultado ["resultado"]="vacio";
    $json['respuesta'][] = $resultado;
    echo json_encode($json);
    }
?>
