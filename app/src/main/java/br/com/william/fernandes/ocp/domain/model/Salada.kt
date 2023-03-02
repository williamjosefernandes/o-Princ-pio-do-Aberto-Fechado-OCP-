package br.com.william.fernandes.ocp.domain.model

class Salada : PratoBase() {

    override fun cortarIngredientes(): List<String> {
        return listOf("alface", "tomate", "cebola", "azeitona", "pepino")
    }

    override fun cozinhar(ingredientes: List<String>): String {
        return ingredientes.joinToString(separator = ", ")
    }

    override fun servir(prato: String): String {
        return "Salada de $prato"
    }
}
