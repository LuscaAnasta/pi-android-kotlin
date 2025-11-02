package com.empresa.vitalogalpha.model

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
data class Usuario(
    val id: Int,
    val nome: String,
    val nomeCompleto: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val dataNascimento: String
){
    /**
     * Propriedade calculada que retorna true se o objeto Usuario for válido, false caso contrário.
     */
    val valido: Boolean
        get() {
            if (id <= 0) return false

            // 1. Verificação de Campos Vazios
            if (nome.isBlank() || nomeCompleto.isBlank() || email.isBlank() || senha.isBlank() || cpf.isBlank() || dataNascimento.isBlank()) {
                return false
            }

            // 2. Validação de Formato e Conteúdo

            if (!isValidNameFormat(nome) || !isValidNameFormat(nomeCompleto)) return false

            if (!isValidEmailFormat(email)) return false

            if (!isValidCpf(cpf)) return false

            if (!isStrongPassword(senha)) return false

            // Assumimos que o formato de entrada é "dd/MM/yyyy"
            if (!isAdultAndValidDate(dataNascimento, "dd/MM/yyyy")) return false

            return true
        }

    // --- FUNÇÕES PRIVADAS DE VALIDAÇÃO ---

    private fun isValidEmailFormat(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidNameFormat(name: String): Boolean {
        return name.matches(Regex("[a-zA-ZáàâãéèêíìîóòôõúùûçÇÁÀÂÃÉÈÊÍÌÎÓÒÔÕÚÙÛ\\s]+"))
    }

    private fun isStrongPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.matches(Regex(".*[A-Z].*"))) return false
        if (!password.matches(Regex(".*[a-z].*"))) return false
        if (!password.matches(Regex(".*[0-9].*"))) return false
        if (!password.matches(Regex(".*[!@#\$%^&*_+\\-=\\[\\]{}:;'\"<>,.?/~`].*"))) return false

        return true
    }

    private fun isValidCpf(cpfRaw: String): Boolean {
        val cpf = cpfRaw.replace("[^0-9]".toRegex(), "")

        if (cpf.length != 11) return false

        if (cpf.all { it == cpf.first() }) return false

        try {
            val numbers = cpf.map { it.toString().toInt() }.toIntArray()

            var sum = 0
            for (i in 0 until 9) {
                sum += numbers[i] * (10 - i)
            }
            var firstVerifier = 11 - (sum % 11)
            if (firstVerifier > 9) firstVerifier = 0
            if (numbers[9] != firstVerifier) return false

            sum = 0
            for (i in 0 until 10) {
                sum += numbers[i] * (11 - i)
            }
            var secondVerifier = 11 - (sum % 11)
            if (secondVerifier > 9) secondVerifier = 0
            if (numbers[10] != secondVerifier) return false

        } catch (e: Exception) {
            return false
        }

        return true
    }

    private fun isAdultAndValidDate(dateString: String, format: String): Boolean {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.isLenient = false

        try {
            val birthDate: Date = sdf.parse(dateString) ?: return false
            // 568025136000L é aproximadamente 18 anos em milissegundos
            val eighteenYearsAgo = Date(System.currentTimeMillis() - 568025136000L)

            if (birthDate.after(Date())) return false

            return birthDate.before(eighteenYearsAgo)

        } catch (e: Exception) {
            return false
        }
    }
}