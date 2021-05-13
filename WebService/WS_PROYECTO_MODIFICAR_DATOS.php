<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios

if(isset($_GET["telefono"]) && isset($_GET["email"]) && isset($_GET["id"])){
    

    $id = $_GET["id"];
    $telefono = $_GET["telefono"];
    $email = $_GET["email"];

    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);

    //Definir sentencia sql
    $call = "UPDATE USUARIOS SET US_TELEFONO = '{$telefono}' , US_EMAIL = '{$email}' WHERE US_ID = '{$id}';";

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
