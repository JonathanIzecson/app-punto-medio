<?php

include "Connections/conexion78.php";

$WS_ID = $_GET['WS_ID'];

$sql = "SELECT WS_ID,WS_CABEZERA,WS_DESCRIPCION FROM NOTAS WHERE WS_ID_USER = '$WS_ID';";

$resultS = mysqli_query ( $conexion78,$sql );

$response = array();
$i = 0;

while($row=mysqli_fetch_array($resultS)){
    $response[$i] = [
        "ID"    => $row['WS_ID'],
        "CABEZERA"       => $row['WS_CABEZERA'],
        "DESCRIPCION"      => $row['WS_DESCRIPCION']
    ];
    $i++;
}
/*while($rowData = mysqli_fetch_array($resultS)){
    $response[i] = $rowData;
    $i++;
}*/
echo json_encode($response);

?>


while($row=mysqli_fetch_array($resultS)){
    $response[$i] = [
        "ID"    => $row['WS_ID'],
        "CABEZERA"       => $row['WS_CABEZERA'],
        "DESCRIPCION"      => $row['WS_DESCRIPCION']
    ];
    i = i + 1;
}