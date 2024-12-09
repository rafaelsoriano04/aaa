<?php

class Conexion
{
  public function conectar()
  {
    define('SERVER', "mysql:host=localhost;dbname=soa");
    define('USER', "root");
    define('PASSWORD', "");

    try {
      $conn = new PDO("mysql:host=localhost;dbname=soa", "root", "");
      return $conn;
    } catch (PDOException $e) {
      die("Error en la conexion: " . $e->getMessage());
    }
  }
}
