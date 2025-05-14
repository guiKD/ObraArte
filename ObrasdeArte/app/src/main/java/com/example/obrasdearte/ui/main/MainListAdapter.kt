package com.example.obrasdearte.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.obrasdearte.R
import com.example.obrasdearte.data.ObraArte
import com.example.obrasdearte.databinding.ObraArteItemBinding
import com.squareup.picasso.Picasso

class MainListAdapter (
    private val onClick: (ObraArte) -> Unit
) : RecyclerView.Adapter<MainListAdapter.ObraViewHolder>() {

    private var lista = listOf<ObraArte>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ObraViewHolder {
        val binding =

            ObraArteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ObraViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ObraViewHolder, position:
        Int
    ) {
        holder.bind(lista[position])
    }

    override fun getItemCount() = lista.size
    fun submitList(novaLista: List<ObraArte>) {
        lista = novaLista
        notifyDataSetChanged()
    }

    inner class ObraViewHolder(private val binding: ObraArteItemBinding):
    RecyclerView.ViewHolder(binding.root) {
        fun bind(obra: ObraArte) {
            binding.tvTitulo.text = obra.titulo
            binding.tvDescricao.text = obra.descricao
            Picasso.get()
                .load(obra.imagem)
                .error(R.drawable.erro)
                .placeholder(R.drawable.loading)
                .into(binding.ivObraDeArte)
            binding.root.setOnClickListener {
                onClick(obra)
            }
        }
    }
}
