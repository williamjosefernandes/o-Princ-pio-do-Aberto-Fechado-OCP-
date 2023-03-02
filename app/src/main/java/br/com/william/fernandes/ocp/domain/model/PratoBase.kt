package br.com.william.fernandes.ocp.domain.model

abstract class PratoBase : Prato {

    override fun preparar(): String {
        val ingredientes = cortarIngredientes()
        val cozido = cozinhar(ingredientes)
        return servir(cozido)
    }

    protected abstract fun cortarIngredientes(): List<String>
    protected abstract fun cozinhar(ingredientes: List<String>): String
    protected abstract fun servir(prato: String): String
}
