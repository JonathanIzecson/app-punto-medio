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
    $consulta = "SELECT S.SR_ID_SERVICIO, S.SR_ENCABEZADO,S.SR_DESCRIPCION,S.SR_PRECIO,S.SR_STATUS,
	    S.SR_PROPOSITO,S.US_ID,U.US_NOMBRE,U.US_APELLIDO,U.US_TELEFONO,U.US_EMAIL
        FROM SERVICIOS S, USUARIOS U WHERE S.US_ID = U.US_ID && S.SR_STATUS = 'Disponible';";
    $resultado = $conexion->query($consulta);

        while($registro=mysqli_fetch_array($resultado)){
            $json['datos'][]=$registro;
    }
    echo json_encode($json);
?>

