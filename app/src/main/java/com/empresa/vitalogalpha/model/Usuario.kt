package com.empresa.vitalogalpha.model

data class Usuario(
    val id: Int,
    val nome: String,
    val nomeCompleto: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val dataNasc: String
)