package com.empresa.vitalogalpha.view.Lista

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.view.Perfil.EditarUsuario

class SobreNosActivity : AppCompatActivity() {

    private lateinit var btnMenuLista: Button
    private lateinit var btnMenuPerfil: Button
    private lateinit var btnMenuRelatorios: Button

    private lateinit var txtNomeUsuario : TextView
    private lateinit var btnConfigUsuario : ImageButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sobre_nos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val sharedPreferences = getSharedPreferences("Dados", Context.MODE_PRIVATE)

        txtNomeUsuario = findViewById(R.id.txtNomeUsuario)
        txtNomeUsuario.text =  sharedPreferences.getString("nome_usuario", "Algo deu Errado")


        btnConfigUsuario = findViewById(R.id.btnConfigUsuario)

        btnConfigUsuario.setOnClickListener {
            val intent = Intent(this@SobreNosActivity, EditarUsuario::class.java)
            startActivity(intent)
        }

        btnMenuLista = findViewById(R.id.btnNavEscrita)
        btnMenuLista.setOnClickListener {
            val intent = Intent(this@SobreNosActivity, ListaActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnMenuRelatorios = findViewById(R.id.btnNavAnotas)
        btnMenuRelatorios.setOnClickListener {
            val intent = Intent(this@SobreNosActivity, RelatoriosActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnMenuPerfil = findViewById(R.id.btnNavProfile)
        btnMenuPerfil.setOnClickListener {
            val intent = Intent(this@SobreNosActivity, SobreNosActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}