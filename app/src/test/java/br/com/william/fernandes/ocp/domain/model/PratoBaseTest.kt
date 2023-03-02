package br.com.william.fernandes.ocp.domain.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class PratoBaseTest {

    @Test
    fun testPreparar() {
        val prato = object : PratoBase() {
            override fun cortarIngredientes(): List<String> {
                return listOf("ingrediente 1", "ingrediente 2")
            }

            override fun cozinhar(ingredientes: List<String>): String {
                return "Prato cozido com " + ingredientes.joinToString(separator = ", ")
            }

            override fun servir(prato: String): String {
                return "Servindo $prato"
            }
        }

        val resultado = prato.preparar()
        assertEquals("Servindo Prato cozido com ingrediente 1, ingrediente 2", resultado)
    }
}
