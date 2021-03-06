package br.edu.ifsp.scl.sdm.listpad.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.listpad.ComprasListaActivity
import br.edu.ifsp.scl.sdm.listpad.R
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra

class ComprasRvAdapter(
    private val onCompraClickListener: ComprasListaActivity,
    private val comprasList: MutableList<Compra>
): RecyclerView.Adapter<ComprasRvAdapter.CompraLayoutHolder>() {

    //viewHolder
    inner class CompraLayoutHolder(layoutCompraBinding: LayoutGenericoBinding): RecyclerView.ViewHolder(layoutCompraBinding.root), View.OnCreateContextMenuListener{
        val nomeTv: TextView = layoutCompraBinding.nomeTv
        val descricaoTv: TextView = layoutCompraBinding.descricaoTv
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraLayoutHolder {
        //Cria uma nova celula a partir da classe de viewBinding
        val layoutCompraBinding = LayoutGenericoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Cria um viewHolder e associa a raiz da instancia da classe de viewBinding (celula)
        return CompraLayoutHolder(layoutCompraBinding)
    }
    //Chamada pelo LayoutManager para alterar o conteúdo de uma célula
    override fun onBindViewHolder(holder: ComprasRvAdapter.CompraLayoutHolder, position: Int) {
        // Busca o item
        val item = comprasList[position]

        //altera os valores das view do viewHolder

        with(holder){
            nomeTv.text = item.nomeCompra
            descricaoTv.text = item.descCompra
        }
        //Seta o onClickListener da celula que esta associada ao ViewHolder como um lambda
        //que chama uma função na MainActivity
        holder.itemView.setOnClickListener{
            onCompraClickListener.OnItemClick(position)
        }
        holder.itemView.setOnLongClickListener{
            posicao = position
            false
        }
    }


    override fun getItemCount(): Int = comprasList.size

    //Posição a ser recuperado no menu de contexto
    var posicao: Int = -1

}