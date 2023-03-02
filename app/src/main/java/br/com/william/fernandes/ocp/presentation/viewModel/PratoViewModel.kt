package br.com.william.fernandes.ocp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.william.fernandes.ocp.domain.model.Massa
import br.com.william.fernandes.ocp.domain.model.Salada
import br.com.william.fernandes.ocp.domain.model.Sopa

class PratoViewModel : ViewModel() {
    val pratos: LiveData<List<String>> get() = _pratos
    private val _pratos = MutableLiveData<List<String>>()

    init {
        val listaPratos = listOf(
            Salada().preparar(),
            Sopa().preparar(),
            Massa().preparar(),
        )
        _pratos.postValue(listaPratos)
    }
}
