package com.empresa.vitalogalpha.view.Lista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.R

class RelatoriosActivity : AppCompatActivity() {

    private lateinit var btnMenuLista: Button
    private lateinit var btnMenuPerfil: Button
    private lateinit var btnMenuRelatorios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatorios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        btnMenuLista = findViewById(R.id.btnNavEscrita)
        btnMenuLista.setOnClickListener {
            val intent = Intent(this@RelatoriosActivity, ListaActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnMenuRelatorios = findViewById(R.id.btnNavAnotas)
        btnMenuRelatorios.setOnClickListener {
            val intent = Intent(this@RelatoriosActivity, RelatoriosActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnMenuPerfil = findViewById(R.id.btnNavProfile)
        btnMenuPerfil.setOnClickListener {
            val intent = Intent(this@RelatoriosActivity, SobreNosActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}