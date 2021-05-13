<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();
    if(isset($_GET["id"]) && isset($_GET["titulo"])){

    $titulo2 = $_GET["titulo"];
    $titulo = str_replace("%20"," ",$titulo2);
    $id = $_GET["id"];
    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);
    //Ejecutar sentencia sql        
    $consulta = "SELECT PR_DESCRIPCION,PR_PRECIO FROM PRODUCTOS WHERE US_ID = '{$id}' && PR_ENCABEZADO = '{$titulo}' UNION SELECT SR_DESCRIPCION,SR_PRECIO FROM SERVICIOS WHERE US_ID = '{$id}' && SR_ENCABEZADO = '{$titulo}';";
    $resultado = $conexion->query($consulta);
    $row_affected = $conexion->affected_rows;
    if($row_affected > 0){
        while($registro=mysqli_fetch_array($resultado)){
            $json['datos'][]=$registro;
    }
    echo json_encode($json);
    }else{
    $result ["resultado"]="vacio";
    $json['datos'][] = $result;
    echo json_encode($json);
    }
}else{
    $result ["resultado"]="sin id";
    $json['datos'][] = $result;
    echo json_encode($json);
}
?>

