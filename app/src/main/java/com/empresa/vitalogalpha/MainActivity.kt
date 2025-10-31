package com.empresa.vitalogalpha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.empresa.vitalogalpha.view.CadastroActivity
import com.empresa.vitalogalpha.view.LoginActivity


class MainActivity : AppCompatActivity() {
    private lateinit var btnLogar: Button
    private lateinit var btnNovaConta: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLogar = findViewById(R.id.btnLogar)

        btnLogar.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnNovaConta = findViewById(R.id.btnNovaContab)

        btnNovaConta.setOnClickListener {
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            startActivity(intent)

        }
    }
}