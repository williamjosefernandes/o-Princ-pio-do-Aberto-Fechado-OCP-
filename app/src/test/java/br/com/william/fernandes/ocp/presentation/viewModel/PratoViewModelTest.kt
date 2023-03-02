package br.com.william.fernandes.ocp.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PratoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<String>>

    private lateinit var viewModel: PratoViewModel

    @Before
    fun setUp() {
        viewModel = PratoViewModel()
    }

    @Test
    fun `verifica se a lista de pratos é retornada corretamente`() {
        viewModel.pratos.observeForever(observer)

        val pratosEsperados = listOf(
            "Salada de alface, tomate, cebola, azeitona, pepino",
            "Sopa de cenoura, batata, cebola, alho, tomate",
            "Macarrão à Bolonhesa com macarrão, molho de tomate, queijo parmesão",
        )

        verify(observer).onChanged(pratosEsperados)
    }
}
