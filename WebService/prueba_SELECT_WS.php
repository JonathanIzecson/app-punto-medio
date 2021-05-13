
<?PHP
$hostname_localhost="localhost";
$database_localhost="bamepoliux";
$username_localhost="root";
$password_localhost="12345678";

$json=array();
 if(isset($_GET["id"])){
  $id=$_GET['id'];
  
  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

  
  $resultado = $conexion->query("SELECT * FROM usuarios WHERE ws_id_user = '{$id}'");
  
   if($registro=mysqli_fetch_array($resultado)){
    $json['usuario'][]=$registro;
   }else{
    $resulta["WS_ID_USER"]=0;
    $resulta["WS_USERNAME"]="WS NO retorna";
    $resulta["WS_PASSWORD"]="WS NO retorna";
    $json['usuario'][]=$resulta;
    echo json_encode($json);
   }
   mysqli_close($conexion);
   echo json_encode($json);
   

 }else{
  $resulta["WS_ID_USER"]=0;
  $resulta["WS_USERNAME"]="WS NO retorna";
  $resulta["WS_PASSWORD"]="WS NO retorna";
  $json['usuario'][]=$resulta;
  echo json_encode($json);
 }
?>