package com.empresa.vitalogalpha.view.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.empresa.vitalogalpha.model.Alimento
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.AlimentoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaAdapter(private val dataSet: List<Alimento>, private val apiService: AlimentoService) :
    RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.txtNomeAlimento)

        val porcao: TextView = view.findViewById(R.id.txtPorcaoAlimento)
        val caloria: TextView = view.findViewById(R.id.txtCaloriaAlimento)
        val btnRemover : Button = view.findViewById(R.id.btnRemover)

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
        viewHolder.caloria.text = "${alimento.caloria.toString()} kcal"


        // Deletar produto ao clicar no botÃƒÂ£o
        viewHolder.btnRemover.setOnClickListener {
            apiService.deletarAlimento(alimento.id).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(it.context, "Alimento deletado com sucesso!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(it.context, "Erro ao deletar o Alimento", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun getItemCount() = dataSet.size
}
