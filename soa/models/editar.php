<?php
include_once "conexion.php";

class CrudUpdate
{
  public static function updateStudent()
  {
    $conn = (new Conexion())->conectar();

    parse_str(file_get_contents("php://input"), $data);

    $cedula = $data['cedula'];
    $nombre = $data['nombre'];
    $apellido = $data['apellido'];
    $direccion = $data['direccion'];
    $telefono = $data['telefono'];

    $insertar = "UPDATE estudiantes SET 
                        nombre = :nombre, 
                        apellido = :apellido, 
                        direccion = :direccion, 
                        telefono = :telefono 
                    WHERE cedula = :cedula";
    $result = $conn->prepare($insertar);
    $result->execute([
      ':nombre' => $nombre,
      ':apellido' => $apellido,
      ':direccion' => $direccion,
      ':telefono' => $telefono,
      ':cedula' => $cedula,
    ]);

    echo json_encode('Se modificó correctamente');
  }
}

// (new CrudInsert())->saveStudent();
