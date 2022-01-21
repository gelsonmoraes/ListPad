package br.edu.ifsp.scl.sdm.listpad.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.CompromissosListaActivity
import br.edu.ifsp.scl.sdm.listpad.R
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compromisso

class CompromissosRvAdapter(
    private val onCompromissoClickListener: CompromissosListaActivity,
    private val compromissoList: MutableList<Compromisso>
) : RecyclerView.Adapter<CompromissosRvAdapter.CompromissosLayoutHolder>() {

    //viewHolder
    inner class CompromissosLayoutHolder(layoutCompromissoBinding: LayoutGenericoBinding) :
        RecyclerView.ViewHolder(layoutCompromissoBinding.root), View.OnCreateContextMenuListener {
        val nomeTv: TextView = layoutCompromissoBinding.nomeTv
        val descricaoTv: TextView = layoutCompromissoBinding.descricaoTv

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompromissosLayoutHolder {
        //Cria uma nova celula a partir da classe de viewBinding
        val layoutCompromissoBinding =
            LayoutGenericoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Cria um viewHolder e associa a raiz da instancia da classe de viewBinding (celula)
        return CompromissosLayoutHolder(layoutCompromissoBinding)
    }

    //Chamada pelo LayoutManager para alterar o conteúdo de uma célula
    override fun onBindViewHolder(holder: CompromissosRvAdapter.CompromissosLayoutHolder, position: Int) {
        // Busca o item
        val item = compromissoList[position]

        //altera os valores das view do viewHolder

        with(holder) {
            nomeTv.text = item.nomeCompromisso
            descricaoTv.text = item.descCompromisso
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener {
            onCompromissoClickListener.OnItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            posicao = position
            false
        }
    }


    override fun getItemCount(): Int = compromissoList.size

    //Posição a ser recuperado no menu de contexto
    var posicao: Int = -1

}