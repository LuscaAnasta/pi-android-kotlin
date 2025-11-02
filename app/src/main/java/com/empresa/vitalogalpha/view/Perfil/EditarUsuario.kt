package com.empresa.vitalogalpha.view.Perfil

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.UsuarioService
import com.empresa.vitalogalpha.credentials.Credenciais
import com.empresa.vitalogalpha.model.Usuario
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarUsuario : AppCompatActivity() {

    private lateinit var btnVoltar : Button
    private lateinit var btnAtualizar : Button

    private lateinit var edtNome : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtSenha : EditText
    private lateinit var edtSenhaConf : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }
        edtNome = findViewById(R.id.edtNome)
        edtEmail  = findViewById(R.id.edtEmail)
        edtSenha  = findViewById(R.id.edtSenha)
        edtSenhaConf  = findViewById(R.id.edtSenhaConf)

        val sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)
        val idUsuario = sharedPreferences.getInt("id", 0)
        edtNome.setText(sharedPreferences.getString("nome_usuario", "Algo deu Errado"))
        edtEmail.setText(sharedPreferences.getString("email", "Algo deu Errado"))


        val cred = Credenciais()
        // ConfiguraÃƒÂ§ÃƒÂ£o do Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(cred.ip) // Substitua pelo seu endereÃƒÂ§o base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(UsuarioService::class.java)

        btnAtualizar = findViewById(R.id.btnAtualizar)
        btnAtualizar.setOnClickListener {
            // Atualizar produto via API
            val usuarioAtualizado = Usuario(
                idUsuario,
                edtNome.text.toString(),
                "null",
                edtEmail.text.toString(),
                edtSenha.text.toString(),
                "null",
                "null"
            )

            apiService.editarUsuario(
                usuarioAtualizado.id,
                usuarioAtualizado.nome,
                usuarioAtualizado.email,
                usuarioAtualizado.senha
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                    Log.d("id", idUsuario.toString())
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditarUsuario, "Usuario atualizado com sucesso!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@EditarUsuario, "Erro na atualização", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@EditarUsuario, "Erro ao atualizar o produto", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

}
