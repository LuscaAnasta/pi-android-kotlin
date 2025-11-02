package com.empresa.vitalogalpha.controller


import com.empresa.vitalogalpha.model.Alimento
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AlimentoService {
    @GET("apis/alimento/ler_alimentos.php")
    fun getAlimentos(): Call<List<Alimento>>

    @FormUrlEncoded
    @POST("apis/alimento/incluir_alimento.php")
    fun incluirAlimento(
        @Field("nome") nome: String,
        @Field("porcao") porcao: String,
        @Field("caloria") caloria: String

    ): Call<Void>

    @FormUrlEncoded
    @POST("apis/alimento/deletar_alimento.php")
    fun deletarAlimento(
        @Field("id") id: Int
    ): Call<Void>


}