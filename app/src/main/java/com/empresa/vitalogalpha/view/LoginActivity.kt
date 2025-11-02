package com.empresa.vitalogalpha.view



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.UsuarioService
import com.empresa.vitalogalpha.model.LoginResponse
import com.empresa.vitalogalpha.view.Lista.ListaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {


    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {

            blockLogin()
        }

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }
    }


    private fun blockLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.112/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val apiService = retrofit.create(UsuarioService::class.java)


        val call = apiService.login(email, password)
        call.enqueue(object : Callback<List<LoginResponse>> {
            override fun onResponse(
            call: Call<List<LoginResponse>>,
            response: Response<List<LoginResponse>>
        ) {
            if (response.isSuccessful && response.body() != null) {
                val loginResponses = response.body()!!
                if (loginResponses.isNotEmpty()) {
                    val usuarioLogado = loginResponses[0]
                    val sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putString("nome_usuario", usuarioLogado.nome)
                        putInt("id", usuarioLogado.id)
                        putString("email", usuarioLogado.email)
                        putString("cpf", usuarioLogado.cpf)
                        putString("senha", usuarioLogado.senha)
                        apply()
                    }

                    val intent = Intent(this@LoginActivity, ListaActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@LoginActivity, "Erro no login", Toast.LENGTH_LONG).show()
            }
        }

            override fun onFailure(call: Call<List<LoginResponse>>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Erro: ${t.message}",
                Toast.LENGTH_LONG).show()
                Log.d("erro", t.message.toString());
            }
        })
    }



}
