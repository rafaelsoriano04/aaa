<?php
include_once "conexion.php";

class CrudInsert
{
  public static function saveStudent()
  {
    $conn = (new Conexion())->conectar();

    $cedula = $_POST['cedula'];
    $nombre = $_POST['nombre'];
    $apellido = $_POST['apellido'];
    $direccion = $_POST['direccion'];
    $telefono = $_POST['telefono'];

    $guardarEstudiantes = "INSERT INTO estudiantes VALUES('$cedula', '$nombre', '$apellido', '$direccion', '$telefono')";
    $result = $conn->prepare($guardarEstudiantes);
    $result->execute();
    
    $data = json_encode('Se inserto');
    print_r($data);
  }
}

