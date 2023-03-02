package br.com.william.fernandes.ocp.domain.model

class Massa : PratoBase() {

    override fun cortarIngredientes(): List<String> {
        return listOf("macarrão", "molho de tomate", "queijo parmesão")
    }

    override fun cozinhar(ingredientes: List<String>): String {
        return ingredientes.joinToString(separator = ", ")
    }

    override fun servir(prato: String): String {
        return "Macarrão à Bolonhesa com $prato"
    }
}
