<?php
include_once "conexion.php";

class CrudDelete
{
  public static function deleteStudents()
  {
    $conn = (new Conexion())->conectar();

    $cedula = $_GET['cedula'];

    $deleteStudents = "DELETE FROM estudiantes WHERE cedula='$cedula'";
    $result = $conn->prepare($deleteStudents);
    $result->execute();

    print_r('Se eliminó');
  }
}

// (new CrudDelete())->deleteStudents();
