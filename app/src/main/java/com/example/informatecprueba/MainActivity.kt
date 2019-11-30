package com.example.informatecprueba

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAvisisWS()

        btnprueba.setOnClickListener{
            startActivity(Intent(this,avisos::class.java))}
    }
    fun consultaXNoControl (v:View) { //CREAMOS UNA CLASE DONDE VERIFICAREMOS QUE EL CAMPO NECESARIO PARA BUSCAR EL ALUMNO  NO ESTE VACIA
        if(etNoControl_buscar.text.isEmpty()){
            Toast.makeText(this, "FALTA INGRESAR NUMERO DE CONTROL", Toast.LENGTH_SHORT).show();
            etNoControl_buscar.requestFocus()
        }else {
            val no = etNoControl_buscar.text.toString()
            val actividad = Intent(this,avisos::class.java)
            actividad.putExtra("ncontrol",no)
            startActivity(actividad)
        }
    }

    fun getAvisisWS() { //funcion que carga la informacion de MySQL a SQLite
        val wsURL = "http://192.168.43.224/Servicios/consultaavisos.php"
        val admin = adminDB(this)
        admin.Ejecuta("DELETE FROM avisos")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, wsURL, null,
            Response.Listener { response ->
                val succ = response["success"]
                val msg = response["message"]
                val avisosJson = response.getJSONArray("avisos")//name usuario (webservice)
                for (i in 0 until avisosJson.length()) {
                    val idaviso = avisosJson.getJSONObject(i).getString("idaviso")
                    val nomasviso = avisosJson.getJSONObject(i).getString("nomaviso")
                    val descripcion = avisosJson.getJSONObject(i).getString("descripcion")
                    val imagen = avisosJson.getJSONObject(i).getString("imagen")
                    val sentencia =
                        "INSERT INTO avisos(idaviso,nomaviso,descripcion,imagen) Values($idaviso,'$nomasviso','$descripcion','$imagen')"
                    var result = admin.Ejecuta(sentencia)
                    Toast.makeText(this, "Información cargada: " + result, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error capa8: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
