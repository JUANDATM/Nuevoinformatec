package com.example.informatecprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalles_aviso.*

class detallesAviso : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_aviso)

        val actividad = intent
        val id = actividad.getIntExtra("idaviso",0)
        val descrip = actividad.getStringExtra("descripcion")
        val nombre = actividad.getStringExtra("nomaviso")
        val imagen = actividad.getStringExtra("imagen")


        tvTitulodetalle.text = descrip
        tvdetalledetalle.text = nombre
        Picasso.get().load(imagen).into(imgdetalle)

    }
}
