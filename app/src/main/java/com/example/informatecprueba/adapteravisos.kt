package com.example.informatecprueba

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.celdaprototipoavisos.view.*

class adapteravisos(private var mListAvisos : List <modeloavisos>,
                    private var mContext : Context,
                    private  var clicklistener: (modeloavisos)-> Unit): RecyclerView.Adapter<adapteravisos.avisosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapteravisos.avisosViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return avisosViewHolder(layoutInflater.inflate(R.layout.celdaprototipoavisos,parent,false))

    }

    override fun onBindViewHolder(holder: adapteravisos.avisosViewHolder, position: Int) {
        holder.bind(mListAvisos[position],mContext,clicklistener)
    }

    override fun getItemCount(): Int = mListAvisos.size

    fun setTask (modeloavisos: List<modeloavisos>){
        mListAvisos = modeloavisos
        notifyDataSetChanged()
    }
    fun getTask(): List<modeloavisos> = mListAvisos

    class avisosViewHolder(itemView:  View): RecyclerView.ViewHolder(itemView){
        fun bind (modelavisos:modeloavisos,context: Context,clickListener: (modeloavisos) -> Unit){
            itemView.tituloCelda.text = modelavisos.nomaviso.toString()
            itemView.txtDescripcion.text = modelavisos.descripcion.toString()
            itemView.setOnClickListener{clickListener(modelavisos)}
        }
    }







}