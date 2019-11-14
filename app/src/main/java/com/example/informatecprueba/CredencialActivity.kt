package com.example.informatecprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_credencial.*
import org.json.JSONObject

class CredencialActivity : AppCompatActivity() {
    companion object {//se declara el companion object para tener acceso a las variables que deseamos traer
        var EXTRA_NoControl = "extraNoControl"
        var EXTRA_Nombre = "ExtraNombre"
        var EXTRA_Carrera = "ExtraCarrera"
        var EXTRA_Foto = "ExtraFoto"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credencial)

        val intent = intent
           var NControl=intent.getStringExtra(EXTRA_NoControl)
            var Nombre=intent.getStringExtra(EXTRA_Nombre)
            var Carrera=intent.getStringExtra(EXTRA_Carrera)
            var Foto=intent.getStringExtra(EXTRA_Foto)
            tvNoControl.text =NControl
            tvCarrera.text = Carrera
            tvNombre.text = Nombre
            Picasso.get().load(Foto).into(imgFotoUser)

    }
}
