package com.empresa.vitalogalpha.view.Lista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.AlimentoService
import com.empresa.vitalogalpha.credentials.Credenciais
import com.empresa.vitalogalpha.model.Alimento
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdicionarAlimento : AppCompatActivity() {

    private lateinit var btnVoltar: Button
    private lateinit var btnAddAlimento: Button

    private lateinit var edtNome : EditText
    private lateinit var edtPorcao : EditText
    private lateinit var edtCaloria : EditText

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
        edtNome = findViewById(R.id.edtNome)
        edtPorcao = findViewById(R.id.edtEmail)
        edtCaloria = findViewById(R.id.edtSenha)
        val cred = Credenciais()
        // ConfiguraÃƒÂ§ÃƒÂ£o do Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(cred.ip) // Substitua pelo seu endereÃƒÂ§o base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(AlimentoService::class.java)

        btnAddAlimento.setOnClickListener {
            val alimento = Alimento(
                0,  // O ID serÃƒÂ¡ gerado automaticamente no banco de dados
                edtNome.text.toString(),
                edtPorcao.text.toString(),
                edtCaloria.text.toString().toFloat()
            )

            // Fazer a requisiÃƒÂ§ÃƒÂ£o para incluir o produto
            apiService.incluirAlimento(
                alimento.nome,
                alimento.porcao,
                alimento.caloria.toString()
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AdicionarAlimento, "Aliemtno adicionado", Toast.LENGTH_LONG).show()
                        finish()
//                        finish()
                    } else {
                        Toast.makeText(this@AdicionarAlimento, "Erro na Aliemtno", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@AdicionarAlimento, "Erro ao incluir o Aliemtno", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}


