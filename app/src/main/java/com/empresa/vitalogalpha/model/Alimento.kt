package com.empresa.vitalogalpha.model

data class Alimento(
    val id: Int,
    val nome: String,
    val porcao: String,
    val caloria: Float
){
    val valido: Boolean
        get() {
            if (id <= 0) {
                return false
            }

            if (nome.isBlank()) {
                return false
            }

            if (porcao.isBlank()) {
                return false
            }

            if (caloria < 0.0f) {
                return false
            }

            return true
        }
}
