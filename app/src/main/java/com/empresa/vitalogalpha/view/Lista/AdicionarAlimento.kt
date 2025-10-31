package com.empresa.vitalogalpha.view.Lista

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.AlimentoService
import com.empresa.vitalogalpha.controller.UsuarioService
import com.empresa.vitalogalpha.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdicionarAlimento : AppCompatActivity() {

    private lateinit var btnVoltar: Button
    private lateinit var btnAddAlimento: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adicionar_alimento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        btnAddAlimento = findViewById(R.id.btnAddAlimento)

        btnAddAlimento.setOnClickListener {

        }
    }

    private fun cadastrarAlimento(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.112/") // Substitua pelo seu endereÃƒÂ§o base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(AlimentoService::class.java)

        Log.d("1", "1");
        val novoAlimento = Usuario(
            0
            )
        Log.d("2", "2");

        apiService.incluirUsuario(
            novoAlimento.cpf,
            novoAlimento.nome,
            novoAlimento.nomeCompleto
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("3", response.toString());
                if (response.isSuccessful) {
                    Toast.makeText(this@CadastroTresActivity, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@CadastroTresActivity, ListaActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                } else {
                    Toast.makeText(this@CadastroTresActivity, "Erro na inclusão", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroTresActivity, "Erro ao incluir o usuario", Toast.LENGTH_LONG).show()
            }
        })
    }
}
