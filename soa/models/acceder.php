<?php
include_once "conexion.php";

class CrudSelect
{
  public static function getStudents()
  {
    $conn = (new Conexion())->conectar();

    $selectEstudiantes = "SELECT * FROM estudiantes";
    $result = $conn->prepare($selectEstudiantes);
    $result->execute();

    $data = $result->fetchAll(PDO::FETCH_ASSOC);

    $jsonData = json_encode($data);

    print_r($jsonData);
  }

  public static function buscarEstudiante()
  {
    $conn = (new Conexion())->conectar();
    $cedula = $_GET['cedula'];

    $selectEstudiantes = "SELECT * FROM estudiantes where cedula = '$cedula'";
    $result = $conn->prepare($selectEstudiantes);
    $result->execute();

    $data = $result->fetchAll(PDO::FETCH_ASSOC);

    $jsonData = json_encode($data);
    print_r($jsonData);
  }
}

// (new CrudSelect())->getStudents();
