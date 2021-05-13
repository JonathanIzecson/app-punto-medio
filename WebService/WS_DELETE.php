<?php

$WS_ID = $_GET['ID_NOTA'];

include "Connections/conexion78.php";

$sql = "DELETE FROM NOTAS WHERE WS_ID = '$WS_ID';";

if ($conexion78->query($sql) === TRUE) {
    $json = array(["estado" => "exito"]);
} else {
    $json = array(["estado" => "error"]);
}
echo json_encode($json);
$conexion78->close();
?>