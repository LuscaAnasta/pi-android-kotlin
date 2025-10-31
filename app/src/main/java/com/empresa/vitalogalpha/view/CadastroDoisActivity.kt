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

class CadastroDoisActivity : AppCompatActivity() {
    private lateinit var btnVoltar: Button
    private lateinit var btnConcluir: Button

    private lateinit var edtCpf: EditText
    private lateinit var edtDataNasc: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_dois)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtCpf = findViewById(R.id.edtCpf)
        edtDataNasc = findViewById(R.id.edtDataNascimento)
        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }
        btnConcluir = findViewById(R.id.btnConcluirCadastro)

        btnConcluir.setOnClickListener {
            cadastroUsuario()
        }
    }

    private fun cadastroUsuario(){
        val nome = intent.getStringExtra("nome")
        val sobrenome = intent.getStringExtra("sobrenome")
        val email = intent.getStringExtra("email")
        val cpf : String = edtCpf.text.toString()
        val dataNasc : String = edtDataNasc.text.toString()

        if(!cpf.isEmpty() && !dataNasc.isEmpty() ){
            val intent = Intent(this@CadastroDoisActivity, CadastroTresActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("sobrenome", sobrenome)
            intent.putExtra("email", email)
            intent.putExtra("cpf", cpf)
            intent.putExtra("dataNasc", dataNasc)
            startActivity(intent)
        }else{
            Toast.makeText(this@CadastroDoisActivity, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }
    }
}