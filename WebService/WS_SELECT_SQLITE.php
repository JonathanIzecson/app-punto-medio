
<?PHP
$hostname_localhost="localhost";
$database_localhost="bamepoliux";
$username_localhost="root";
$password_localhost="12345678";

$json=array();
 if(isset($_GET["id"])){
  $id=$_GET['id'];
  
  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

  
  $resultado = $conexion->query("SELECT * FROM almacen WHERE codigo = '{$id}'");
  $afectada = $conexion->affected_rows;

  if($afectada > 0){
     if($registro=mysqli_fetch_array($resultado)){
   $json['producto'][]=$registro;
   }
  }else{
    $resulta["estado"]="inexistente";
    $json['producto'][]=$resulta;
   }
   mysqli_close($conexion);
   echo json_encode($json);
 }
?>