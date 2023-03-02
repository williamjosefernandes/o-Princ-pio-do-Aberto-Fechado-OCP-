package br.com.william.fernandes.ocp.domain.model

class Sopa : PratoBase() {

    override fun cortarIngredientes(): List<String> {
        return listOf("cenoura", "batata", "cebola", "alho", "tomate")
    }

    override fun cozinhar(ingredientes: List<String>): String {
        return ingredientes.joinToString(separator = ", ")
    }

    override fun servir(prato: String): String {
        return "Sopa de $prato"
    }
}
