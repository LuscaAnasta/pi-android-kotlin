package com.empresa.vitalogalpha.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.UsuarioService
import com.empresa.vitalogalpha.model.Usuario
import com.empresa.vitalogalpha.view.Lista.ListaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CadastroTresActivity : AppCompatActivity() {

    private lateinit var btnVoltar: Button
    private lateinit var btnConcluir: Button

    private lateinit var edtSenha: EditText
    private lateinit var edtSenhaConf: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_tres)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtSenha = findViewById(R.id.edtSenha)
        edtSenhaConf = findViewById(R.id.edtSenhaConf)

        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }
        btnConcluir = findViewById(R.id.btnConcluirCadastro)



        btnConcluir.setOnClickListener {

            dadosCadastro()
        }
    }

    private fun dadosCadastro(){
        val senha : String = edtSenha.text.toString()
        val senhaConf : String = edtSenhaConf.text.toString()
        if(!senha.isEmpty() && !senhaConf.isEmpty()){
            if (senha == senhaConf) {
                cadastrarUsuario()
            }else{
                Toast.makeText(this@CadastroTresActivity, "Senhas não são iguais", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this@CadastroTresActivity, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun cadastrarUsuario(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.112/") // Substitua pelo seu endereÃƒÂ§o base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(UsuarioService::class.java)

        Log.d("1", "1");
        val novoUsuario = Usuario(
            0,
            intent.getStringExtra("nome").toString(),
            intent.getStringExtra("sobrenome").toString(),
            intent.getStringExtra("email").toString(),
            edtSenha.text.toString(),
            intent.getStringExtra("cpf").toString(),
            intent.getStringExtra("dataNasc").toString(),

            )
        Log.d("2", "2");

        apiService.incluirUsuario(
            novoUsuario.cpf,
            novoUsuario.nome,
            novoUsuario.nomeCompleto,
            novoUsuario.email,
            novoUsuario.senha,
            novoUsuario.dataNasc
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
