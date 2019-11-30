<?php 
    include_once("BaseDatos.php");
    header('Content-type: application/json; charset=utf-8');
    $method=$_SERVER['REQUEST_METHOD'];

    $obj = json_decode( file_get_contents('php://input') );   
    $objArr = (array)$obj;
	if (empty($objArr))
    {
		$response["success"] = 422;  //No encontro información 
        $response["message"] = "Error: checar json entrada";
        header($_SERVER['SERVER_PROTOCOL']." 422  Error: faltan parametros de entrada json ");		
    }
    else
    {
        $response = array();
        $usr= $objArr['usr'];
        $cont= $objArr['pwd'];
        $alu= $objArr['alumno'];//arreglo json
        $res = obj2array($alu);//convierte el json en una array
        //Validar que el usuario tiene permisos par actualizar la tabla de alumnos
        //
        $result =0;
        foreach($res as $value){
            $result = InsActAlumnos($value);

        }
        
        if ($result = 1) {
            $response["success"] = "201";
            $response["message"] = "EXITO!! Se Respaldaron Los Alumnos";
        }
        else{
            $response["success"] = "409";
            $response["message"] = "ERROR: Alumnos no se respaldaron";
            header($_SERVER['SERVER_PROTOCOL'] . " 409  Conflicto al Insertar ");
        }
    }
    echo json_encode($response);
?>