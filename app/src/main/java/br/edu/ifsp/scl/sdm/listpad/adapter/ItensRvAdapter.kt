package br.edu.ifsp.scl.sdm.listpad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.OnItemClickListener
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Itens

class ItensRvAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val itensList: MutableList<Itens>
): RecyclerView.Adapter<ItensRvAdapter.ItemLayoutHolder>() {

    //fixador de visualização
    inner class ItemLayoutHolder(layoutItemBinding: LayoutGenericoBinding): RecyclerView.ViewHolder(layoutItemBinding.root){
        val nomeTv: TextView = layoutItemBinding.nomeTv
        val descricaoTv: TextView = layoutItemBinding.descricaoTv
    }

    // Chamada quando uma nova celula precisa ser criada pelo LayoutManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLayoutHolder {
        //Cria uma nova celula a partir da classe de viewBinding
        val layoutItemBinding = LayoutGenericoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Cria um viewHolder e associa a raiz da instancia da classe de viewBinding (celula)
        return ItemLayoutHolder(layoutItemBinding)
    }

    //Chamada pelo LayoutManager para alterar o conteúdo de uma célula
    override fun onBindViewHolder(holder: ItemLayoutHolder, position: Int) {
        // Busca o item
        val item = itensList[position]

        //altera os valores das view do viewHolder

        with(holder){
            holder.nomeTv.text = item.nomeLista
            holder.descricaoTv.text = item.descLista
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener{
            onItemClickListener.OnItemClick(position)
        }

    }

    override fun getItemCount(): Int = itensList.size
}