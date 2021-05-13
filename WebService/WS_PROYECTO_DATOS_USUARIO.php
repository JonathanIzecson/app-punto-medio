<?php

//Definir credenciales
$host = "localhost";
$password = "12345678";
$database = "proyecto";
$user = "root";

//Crear arreglo JSon
$json = array();

//Validacion de campos no vacios
if(isset($_GET["username"])){
    $username = $_GET["username"];
    
    //Crear conexion a la base
    $conexion = new mysqli($host, $user, $password, $database);



    //Ejecutar sentencia sql        
    $consulta = "SELECT * FROM USUARIOS WHERE US_USERNAME = '{$username}';";
    $resultado = $conexion->query($consulta);


        if($registro=mysqli_fetch_array($resultado)){
            $json['datos'][]=$registro;
            echo json_encode($json);

    }else{
        //Error si no se pudo insertar
        $resultadoAcceso ["disponible"]="inexistente";
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
