package com.empresa.vitalogalpha.view.Lista

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empresa.vitalogalpha.R
import com.empresa.vitalogalpha.controller.AlimentoService
import com.empresa.vitalogalpha.model.Alimento
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Source
import org.intellij.lang.annotations.JdkConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ListaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListaAdapter

    private lateinit var btnAdicionarAlimento: FloatingActionButton


    private lateinit var btnMenuLista: Button
    private lateinit var btnMenuPerfil: Button
    private lateinit var btnMenuRelatorios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        recyclerView = findViewById(R.id.recyclerViewAlimentos)
        recyclerView.layoutManager = LinearLayoutManager(this)


        btnMenuLista = findViewById(R.id.btnNavEscrita)
        btnMenuLista.setOnClickListener {
            val intent = Intent(this@ListaActivity, ListaActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnMenuRelatorios = findViewById(R.id.btnNavAnotas)
        btnMenuRelatorios.setOnClickListener {
            val intent = Intent(this@ListaActivity, RelatoriosActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnMenuPerfil = findViewById(R.id.btnNavProfile)
        btnMenuPerfil.setOnClickListener {
            val intent = Intent(this@ListaActivity, SobreNosActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdicionarAlimento = findViewById(R.id.btnAdicionarAlimento)

        btnAdicionarAlimento.setOnClickListener {
            val intent = Intent(this@ListaActivity, AdicionarAlimento::class.java)
            startActivity(intent)

        }
        // ConfiguraÇÃo do Logging Interceptor
        val logging = HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // ConfiguraÇÃo do OkHttpClient com o interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        // Configuração do Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.112/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val apiService = retrofit.create(AlimentoService::class.java)
        apiService.getAlimentos().enqueue(object : Callback<List<Alimento>> {
            override fun onResponse(call: Call<List<Alimento>>, response: Response<List<Alimento>>) {
                if (response.isSuccessful) {
                    val alimentos = response.body() ?: emptyList()
                    adapter = ListaAdapter(alimentos, apiService)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("API Error", "Response not successful. Code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Alimento>>, t: Throwable) {
                Log.e("API Failure", "Error fetching products", t)
            }
        })
    }
}
