<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();
    if(isset($_GET["id"])){

    $id = $_GET["id"];
    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);
    //Ejecutar sentencia sql        
    $consulta = "SELECT * FROM PRODUCTOS WHERE US_ID = '{$id}' UNION SELECT * FROM SERVICIOS WHERE US_ID = '{$id}';";
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

