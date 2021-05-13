
<?PHP
$hostname_localhost="localhost";
$database_localhost="bamepoliux";
$username_localhost="root";
$password_localhost="12345678";

$json=array();
 if(isset($_GET["username"]) && isset($_GET["password"])){
  $username=$_GET['username'];
  $password=$_GET['password'];

  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
 
  $insert="INSERT INTO usuarios(ws_id_user, ws_username, ws_password) VALUES (null,'{$username}','{$password}')";
  
  
 
  if($conexion->query($insert)===TRUE){
   
   
   $resultado = $conexion->query("SELECT * FROM usuarios WHERE ws_username = '{$username}'");
   //$resultado=mysqli_query($conexion, $consulta);
  
   if($registro=mysqli_fetch_array($resultado)){
    $json['usuario'][]=$registro;
   }
   mysqli_close($conexion);
   echo json_encode($json);
   
  }else{
   $resulta["ws_id_user"]=0;
   $resulta["ws_username"]="NO registra";
   $resulta["ws_password"]="NO registra";
   $json['usuario'][]=$resulta;
   echo json_encode($json);
  }
 }else{
  $resulta["ws_id_user"]=0;
  $resulta["ws_username"]="WS NO retorna";
  $resulta["ws_password"]="WS NO retorna";
  $json['usuario'][]=$resulta;
  echo json_encode($json);
 }
?>