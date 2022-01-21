package br.edu.ifsp.scl.sdm.listpad.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.GeralListaActivity
import br.edu.ifsp.scl.sdm.listpad.R
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Geral

class GeralRvAdapter(
    private val onGeralClickListener: GeralListaActivity,
    private val geralList: MutableList<Geral>
) : RecyclerView.Adapter<GeralRvAdapter.GeralLayoutHolder>() {

    //viewHolder
    inner class GeralLayoutHolder(layoutGeralBinding: LayoutGenericoBinding) :
        RecyclerView.ViewHolder(layoutGeralBinding.root), View.OnCreateContextMenuListener {
        val nomeTv: TextView = layoutGeralBinding.nomeTv
        val descricaoTv: TextView = layoutGeralBinding.descricaoTv

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeralLayoutHolder {
        //Cria uma nova celula a partir da classe de viewBinding
        val layoutGeralBinding =
            LayoutGenericoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Cria um viewHolder e associa a raiz da instancia da classe de viewBinding (celula)
        return GeralLayoutHolder(layoutGeralBinding)
    }

    //Chamada pelo LayoutManager para alterar o conteúdo de uma célula
    override fun onBindViewHolder(holder: GeralRvAdapter.GeralLayoutHolder, position: Int) {
        // Busca o item
        val item = geralList[position]

        //altera os valores das view do viewHolder

        with(holder) {
            nomeTv.text = item.nomeGeral
            descricaoTv.text = item.descGeral
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener {
            onGeralClickListener.OnItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            posicao = position
            false
        }
    }


    override fun getItemCount(): Int = geralList.size

    //Posição a ser recuperado no menu de contexto
    var posicao: Int = -1

}