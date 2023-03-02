package br.com.william.fernandes.ocp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.william.fernandes.ocp.R

class PratoAdapter(val pratos: List<String>) : RecyclerView.Adapter<PratoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PratoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prato, parent, false)
        return PratoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PratoViewHolder, position: Int) {
        holder.bind(pratos[position])
    }

    override fun getItemCount(): Int {
        return pratos.size
    }
}

class PratoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvNomePrato: TextView = itemView.findViewById(R.id.tvNomePrato)

    fun bind(prato: String) {
        tvNomePrato.text = prato
    }
}
