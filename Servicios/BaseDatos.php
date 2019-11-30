<?php
//---------------------------------------------------------------------
// Utilerias de Bases de Dato
// Alejandro Guzmán Zazueta
// Enero 2019
//---------------------------------------------------------------------

try{
       // $Cn = new PDO('pgsql:host=localhost;port=5432;dbname=ProyectoX;user=postgres;password=hola');
        $Cn = new PDO('mysql:host=localhost; dbname=pruebaestudiantes','root','');
        $Cn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //$Cn->exec("SET CLIENT_ENCODING TO 'UTF8';");
        $Cn->exec("SET CHARACTER SET utf8");
}catch(Exception $e){
    die("Error: " . $e->GetMessage());
}
// Función para ejecutar consultas SELECT
function Consulta($query)
{
    global $Cn;
    try{    
        $result =$Cn->query($query);
        $resultado = $result->fetchAll(PDO::FETCH_ASSOC); 
        $result->closeCursor();
        return $resultado;
    }catch(Exception $e){
        die("Error en la LIN: " . $e->getLine() . ", MSG: " . $e->GetMessage());
    }
}
//------------------------------------------------------------
function consultaAlumno($nocon){
    $query = "SELECT * FROM estudiantes WHERE NoControl = '$nocon'";
    return Consulta($query);
}
function consultaAlumnos(){
    $query = "SELECT * FROM estudiantes ORDER BY Nombre";
    return Consulta($query);
}


//--------------------------------------AVISOS-----------------------------------------------


function consultaAvisos(){
    $query = "SELECT * FROM avisos ORDER BY nomaviso";
    return Consulta($query);
}

