package com.empresa.vitalogalpha.view.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.empresa.vitalogalpha.model.Alimento
import com.empresa.vitalogalpha.R

class ListaAdapter(
    private val dataSet: List<Alimento>) :
    RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.txtNomeAlimento)
        val porcao: TextView = view.findViewById(R.id.txtPorcaoAlimento)
        val caloria: TextView = view.findViewById(R.id.txtCaloriaAlimento)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_alimentos, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val alimento = dataSet[position]
        viewHolder.nome.text = alimento.nome
        viewHolder.porcao.text = alimento.porcao
        viewHolder.caloria.text = alimento.caloria.toString()
    }

    override fun getItemCount() = dataSet.size
}
