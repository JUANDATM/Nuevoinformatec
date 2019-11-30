<?php 
    include_once("./BaseDatos.php");
    header('Content-type: application/json; charset=utf-8');
    $method=$_SERVER['REQUEST_METHOD'];
    $response = array();
    $result = consultaAvisos();
    if (!empty($result)) {
        $response["success"] = "202";
        $response["message"] = "Alumnos encontrados";
        $response["avisos"] = array();
        foreach ($result as $tupla)
        {
            array_push($response["avisos"], $tupla);
        }
    }
    else{
        $response["success"] = "204";
        $response["message"] = "Alumnos no encontrados";
        header($_SERVER['SERVER_PROTOCOL'] . " 500  Error interno del servidor ");
    }
    echo json_encode($response);
?>