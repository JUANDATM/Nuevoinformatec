package com.example.informatecprueba

import android.content.Intent
import android.os.AsyncTask
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_avisos.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class avisos : AppCompatActivity() {
    var idaviso : Int = 0
    var nomaviso : String = ""
    var descripcion : String = ""
    var imagen : String = ""
    var ncontrol : String = ""
    var nombre : String =""
    var carrera : String = ""
    var foto : String = ""
    var qr : String = ""
     var no : String = ""
    var hilo : ObtenerUnServicioWeb? = null
    private lateinit var viewAdapter: adapteravisos
    private lateinit var viewManager: RecyclerView.LayoutManager
    val avisoslista : List<modeloavisos> = ArrayList()
    var wsConsultar : String = "http://192.168.43.224/Servicios/MostrarAlumno.php"//AGREGAMOS LA DIRECCIONDE DONDE SE VA A CONSUMIR EL SERVICIO WEB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avisos)
        etncontrol.text = no
            viewManager = LinearLayoutManager(this)
        viewAdapter = adapteravisos(
            avisoslista,
            this,
            { avisos: modeloavisos -> onItemClickListener(avisos) })
        avisosrecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@avisos,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val avisos = viewAdapter.getTask()
                val admin = adminDB(baseContext)
               // if (admin.Ejecuta(
              //          "Delete From aviso Where idaviso ="
              //                  + avisos[position].idaviso
               //     ) == true
              //  ) {
                    retrieveAvisos()
                //}
            }
        }).attachToRecyclerView(avisosrecycler)
    }

    private fun onItemClickListener(avisos: modeloavisos) {
       val actividad = Intent(this, detallesAviso::class.java)
        actividad.putExtra("idaviso",avisos.idaviso)
        actividad.putExtra("nomaviso",avisos.nomaviso)
        actividad.putExtra("descripcion",avisos.descripcion)
        actividad.putExtra("imagen", avisos.imagen)
        startActivity(actividad)
    }

    override fun onResume() {
        super.onResume()
        retrieveAvisos()
    }

    private fun retrieveAvisos() {
        val avisoX = getavisos()
        viewAdapter.setTask(avisoX!!)

    }

    fun getavisos(): MutableList<modeloavisos> {
        var avisos: MutableList<modeloavisos> = ArrayList()
        val admin = adminDB(this)
        val tupla = admin.Consulta("Select * From avisos Order By idaviso")
        while (tupla!!.moveToNext()) {
            idaviso = tupla.getInt(0)
            nomaviso = tupla.getString(1)
            descripcion = tupla.getString(2)
            imagen = tupla.getString(3)
            avisos.add(modeloavisos(idaviso,nomaviso,descripcion,imagen))
        }
        tupla.close()
        admin.close()
        return avisos

    }
    fun consultaXNoControl () { //CREAMOS UNA CLASE DONDE VERIFICAREMOS QUE EL CAMPO NECESARIO PARA BUSCAR EL ALUMNO  NO ESTE VACIA
        val actividad = intent
        if(actividad != null && actividad.hasExtra("ncontrol")) no = actividad.getStringExtra("ncontrol")

            hilo = ObtenerUnServicioWeb()
            hilo?.execute("Consulta", "$no","","","")


    }

    inner class ObtenerUnServicioWeb(): AsyncTask<String, String, String>(){

        override fun doInBackground(vararg params: String?): String {
            var Url : URL? = null
            var sResultado = ""
            try {
                val urlConn: HttpURLConnection
                //val printout: DataOutputStream
                // var input : DataOutputStream
                if (params[0].toString() == "Consulta") {//Aqui se decide que cuando dice Consulta en el param en en elemnto 0 se hara la cwsconsulta
                    Url = URL(wsConsultar)
                }
                urlConn = Url?.openConnection() as HttpURLConnection
                urlConn.doInput = true
                urlConn.doOutput = true
                urlConn.useCaches=false
                urlConn.setRequestProperty("Content-Type","Aplication/json")//TIPO DE DATO QUE RESIBIRA
                urlConn.setRequestProperty("Accept","aplication/json")
                urlConn.connect()
                //PREPARAR LOS DATOS A ENVIAR AL WEB SERVICE
                val jsonParam = JSONObject()
                jsonParam.put("NoControl",params[1])
                jsonParam.put("Carrera",params[2])
                jsonParam.put("Nombre",params[3])
                jsonParam.put("Foto",params[4])
                val os = urlConn.outputStream
                val writer = BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                writer.write(jsonParam.toString())
                writer.flush()
                writer.close()
                val respuesta= urlConn.responseCode
                val result = StringBuilder()
                if (respuesta == HttpURLConnection.HTTP_OK){
                    val inStream : InputStream = urlConn.inputStream
                    val isReader = InputStreamReader(inStream)
                    val bReader = BufferedReader(isReader)
                    var tempStr : String?
                    while (true){
                        tempStr = bReader.readLine()
                        if (tempStr == null){
                            break
                        }
                        result.append(tempStr)
                    }
                    urlConn.disconnect()
                    sResultado = result.toString()
                }
            }catch (e: MalformedURLException){
                Log.d("JDTM",e.message)
            }catch (e: IOException){
                Log.d("JDTM",e.message)
            }catch (e: JSONException){
                Log.d("JDTM",e.message)
            }catch (e: Exception){
                Log.d("JDTM",e.message)
            }
            return sResultado
        }//Fin doInBackground    //Metodos que se van a utilizar
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var no = ""
            var nom = ""
            var carr = ""
            var fot = ""
            var qrr = ""
            try {
                val respuestaJSON = JSONObject(result)
                val resultJSON = respuestaJSON.getString("success")
                // val msgJSON = respuestaJSON.getString("message")
                when (resultJSON) {
                    "200" -> {
                        val alumnoJSON = respuestaJSON.getJSONArray("estudiantes")
                        if (alumnoJSON.length() >= 1) {
                            no = alumnoJSON.getJSONObject(0).getString("NoControl")
                            nom = alumnoJSON.getJSONObject(0).getString("Nombre")
                            carr = alumnoJSON.getJSONObject(0).getString("Carrera")
                            fot = alumnoJSON.getJSONObject(0).getString("Foto")
                            qrr = alumnoJSON.getJSONObject(0).getString("QR")
                            ncontrol = no
                            nombre =nom
                            carrera = carr
                            foto =  fot
                            qr = qrr
                            Toast.makeText(baseContext, "ALUMNO ENCONTRADO", Toast.LENGTH_SHORT).show()
                            val intent = Intent(baseContext,CredencialActivity::class.java)
                            intent.putExtra(CredencialActivity.EXTRA_NoControl,ncontrol)
                            intent.putExtra(CredencialActivity.EXTRA_Nombre,nombre)
                            intent.putExtra(CredencialActivity.EXTRA_Carrera,carrera)
                            intent.putExtra(CredencialActivity.EXTRA_Foto,foto)
                            intent.putExtra(CredencialActivity.EXTRA_QR,qr)
                            startActivity(intent)
                        }
                    }
                    "204" -> {// SI LA OPERACION ES ERRONEA SE MANDA EL SIGUIENTE MENSAJE
                        Toast.makeText(baseContext, "ERROR : ALUMNO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                    }
                    "409" -> {// SI POR ALGUNA RAZON NO EXISTE LA OPERACON , SE ENVIA LA SIGUIENTE
                        Toast.makeText(baseContext, "ERROR : AL AGREGAR ALUMNO", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (e: JSONException) {
                Log.d("JDTM", e.message)
            }catch (e: Exception) {
                Log.d("JDTM", e.message)
            }

        }//fin  onPostExecute //Metodos que se van a utilizar

    }// Fin  ObtenerUnServicioWeb
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.credencialmenu -> {
                startActivity(Intent(this, CredencialActivity::class.java))
                consultaXNoControl()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


}
