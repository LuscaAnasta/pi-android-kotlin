package com.empresa.vitalogalpha.model

data class LoginResponse(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val cpf: String
)


