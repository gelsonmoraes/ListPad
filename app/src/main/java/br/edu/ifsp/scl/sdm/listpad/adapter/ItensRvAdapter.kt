package br.edu.ifsp.scl.sdm.listpad.adapter

import br.edu.ifsp.scl.sdm.listpad.*
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Itens
import br.edu.ifsp.scl.sdm.listpad.db.DatabaseHandler

class ItensRvAdapter(
    private val onItemClickListener: MainActivity,
    private val itensList: MutableList<Itens>
): RecyclerView.Adapter<ItensRvAdapter.ItemLayoutHolder>() {

    //viewHolder
    inner class ItemLayoutHolder(layoutItemBinding: LayoutGenericoBinding): RecyclerView.ViewHolder(layoutItemBinding.root), View.OnCreateContextMenuListener{
        val nomeTv: TextView = layoutItemBinding.nomeTv
        val descricaoTv: TextView = layoutItemBinding.descricaoTv
        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            view: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            MenuInflater(view?.context).inflate(R.menu.context_menu_main, menu)
        }

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
            nomeTv.text = item.nomeLista
            descricaoTv.text = item.descLista
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener{
            onItemClickListener.OnItemClick(position)
        }
        holder.itemView.setOnLongClickListener{
            posicao = position
            false
        }

    }

    override fun getItemCount(): Int = itensList.size

    //Posição a ser recuperado no menu de contexto
    var posicao: Int = -1
}