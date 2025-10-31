package com.empresa.vitalogalpha.controller


import com.empresa.vitalogalpha.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioService {
    @GET("apis/usuario/login.php")
    fun login(
        @Query("usuario") usuario: String,
        @Query("senha") senha: String
    ): Call<List<LoginResponse>>

    @FormUrlEncoded
    @POST("apis/usuario/incluir_usuario.php")
    fun incluirUsuario(
        @Field("cpf") cpf: String,
        @Field("nome") nome: String,
        @Field("nomeCompleto") nomeCompleto: String,
        @Field("email") email: String,
        @Field("senha") senha: String,
        @Field("dataNascimento") dataNasc: String
    ): Call<Void>

}