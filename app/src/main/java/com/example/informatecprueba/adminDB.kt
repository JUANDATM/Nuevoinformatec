package com.example.informatecprueba

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class adminDB(context: Context): SQLiteOpenHelper(context,DATABASE,null,1){
    companion object{ val DATABASE = "eleccion"}
    override fun onCreate(db: SQLiteDatabase?) {//Se crea la tabla usuario, candidato y voto
        db?.execSQL("CREATE TABLE avisos(idaviso integer PRIMARY KEY, nomaviso TEXT," +
                "descripcion TEXT,imagen TEXT)")

    }
    fun Ejecuta(sentencia: String):Boolean{ //Función para ejecutar un Insert, update o delete
        try {
            val DB = this.writableDatabase
            DB.execSQL(sentencia)
            DB.close()
            return true
        }catch (ex:Exception){ return false}
    }
    fun Consulta(query:String): Cursor?{ //Función para ejecutar un select
        try {
            val DB = this.readableDatabase
            return DB.rawQuery(query,null)
        }catch (ex:Exception){ return null}
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}