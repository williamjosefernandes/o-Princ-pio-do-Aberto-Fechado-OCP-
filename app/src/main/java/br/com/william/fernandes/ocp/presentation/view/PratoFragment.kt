package br.com.william.fernandes.ocp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.william.fernandes.ocp.R
import br.com.william.fernandes.ocp.presentation.PratoAdapter
import br.com.william.fernandes.ocp.presentation.viewModel.PratoViewModel

class PratoFragment : Fragment() {

    private val viewModel: PratoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prato, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.pratos.observe(viewLifecycleOwner) { pratos ->
            recyclerView.adapter = PratoAdapter(pratos)
        }

        return view
    }
}
