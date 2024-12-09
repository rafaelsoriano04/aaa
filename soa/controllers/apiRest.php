<?php
// RequestMethod (get, put, post, delete)
include '../models/acceder.php';
include '../models/guardar.php';
include '../models/borrar.php';
include '../models/editar.php';

$opc = $_SERVER['REQUEST_METHOD'];

switch ($opc) {
  case 'GET':
    if (isset($_GET['cedula'])) {
      CrudSelect::buscarEstudiante();
    } else {
      CrudSelect::getStudents();
    }
    break;
  case 'POST':
    CrudInsert::saveStudent();
    break;
  case 'DELETE':
    CrudDelete::deleteStudents();
    break;
  case 'PUT':
    CrudUpdate::updateStudent();
    break;
}
