package com.empresa.vitalogalpha.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R

class CadastroActivity : AppCompatActivity() {
    private lateinit var btnVoltar: Button
    private lateinit var btnContinuar: Button

    private lateinit var edtNome: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtSobrenome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNome = findViewById(R.id.edtNome)
        edtSobrenome = findViewById(R.id.edtSobrenome)
        edtEmail = findViewById(R.id.edtEmail)


        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
                finish()
            }
        btnContinuar = findViewById(R.id.btnConcluirCadastro)

        btnContinuar.setOnClickListener {
            dadosCadastro()
        }
    }


    private fun dadosCadastro(){
        val nome : String = edtNome.text.toString()
        val sobrenome : String = edtSobrenome.text.toString()
        val email : String = edtEmail.text.toString()

        if(!email.isEmpty() && !nome.isEmpty() && !sobrenome.isEmpty() ){
            val intent = Intent(this@CadastroActivity, CadastroDoisActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("sobrenome", sobrenome)
            intent.putExtra("email", email)
            startActivity(intent)
        }else{
            Toast.makeText(this@CadastroActivity, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }
    }
}