<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios

if(isset($_GET["titulo"]) && isset($_GET["id"])){
    

    $id = $_GET["id"];
    $titulo2 = $_GET["titulo"];
    $titulo = str_replace("%20"," ",$titulo2);

    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);

    //Definir sentencia sql
    $call = "DELETE FROM PRODUCTOS WHERE PR_ENCABEZADO = '{$titulo}' && US_ID = '{$id}';";

    //Ejecutar sentencia sql
    $conexion->query($call);

    //Determinar si se realizo la accion
    $row_affected = $conexion->affected_rows;
    if($row_affected == 1){
        $resultado ["resultado"]="exito";
        $json['respuesta'][] = $resultado;
        echo json_encode($json);
    }else{
        
    //Definir sentencia sql
    $call = "DELETE FROM SERVICIOS WHERE SR_ENCABEZADO = '{$titulo}' && US_ID = '{$id}';";
    //Ejecutar sentencia sql
    $conexion->query($call);
    //Determinar si se realizo la accion
    $row_affected = $conexion->affected_rows;
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
}
}
?>