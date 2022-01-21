package br.edu.ifsp.scl.sdm.listpad.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.R
import br.edu.ifsp.scl.sdm.listpad.TarefasListaActivity
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Tarefa

class TarefasRvAdapter(
    private val onTarefaClickListener: TarefasListaActivity,
    private val comprasList: MutableList<Tarefa>
) : RecyclerView.Adapter<TarefasRvAdapter.TarefaLayoutHolder>() {

    //viewHolder
    inner class TarefaLayoutHolder(layoutTarefaBinding: LayoutGenericoBinding) :
        RecyclerView.ViewHolder(layoutTarefaBinding.root), View.OnCreateContextMenuListener {
        val nomeTv: TextView = layoutTarefaBinding.nomeTv
        val descricaoTv: TextView = layoutTarefaBinding.descricaoTv

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            view: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            MenuInflater(view?.context).inflate(R.menu.context_menu_listas, menu)
        }

    }

    // Chamada quando uma nova celula precisa ser criada pelo LayoutManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaLayoutHolder {
        //Cria uma nova celula a partir da classe de viewBinding
        val layoutTarefaBinding =
            LayoutGenericoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Cria um viewHolder e associa a raiz da instancia da classe de viewBinding (celula)
        return TarefaLayoutHolder(layoutTarefaBinding)
    }

    //Chamada pelo LayoutManager para alterar o conteúdo de uma célula
    override fun onBindViewHolder(holder: TarefasRvAdapter.TarefaLayoutHolder, position: Int) {
        // Busca o item
        val item = comprasList[position]

        //altera os valores das view do viewHolder

        with(holder) {
            nomeTv.text = item.nomeTarefa
            descricaoTv.text = item.descTarefa
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener {
            onTarefaClickListener.OnItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            posicao = position
            false
        }
    }


    override fun getItemCount(): Int = comprasList.size

    //Posição a ser recuperado no menu de contexto
    var posicao: Int = -1

}