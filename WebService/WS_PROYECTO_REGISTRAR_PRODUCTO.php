<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios
if(isset($_GET["encabezado"]) || isset($_GET["descripcion"]) || isset($_GET["precio"]) || isset($_GET["proposito"]) || isset($_GET["idUser"])){
    $encabezado2 = $_GET["encabezado"];
    $encabezado = str_replace("%20", " ", $encabezado2);
    $descripcion2 = $_GET["descripcion"];
    $descripcion = str_replace("%20", " ", $descripcion2);
    $precio2 = $_GET["precio"];
    $precio = str_replace("%20", " ", $precio2);
    $proposito = $_GET["proposito"];
    $idUser = $_GET["idUser"];

    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);
    //Definir sentencia sql
    $call = "CALL IN_PRODUCTOS(null,'{$encabezado}','{$descripcion}','{$precio}','{$proposito}','{$idUser}');";

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
