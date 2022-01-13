package br.edu.ifsp.scl.sdm.listpad.adapter
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.listpad.R
import br.edu.ifsp.scl.sdm.listpad.databinding.LayoutGenericoBinding
import br.edu.ifsp.scl.sdm.listpad.model.Itens

class ItemAdapter(
        val contexto: Context,
        val itensList: MutableList<Itens>
    ) : ArrayAdapter<Itens>(contexto, R.layout.layout_generico, itensList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemLayout : View = if (convertView != null){
            //view já existe
            convertView
        }
        else{
            //view precisa ser inflada
            val layoutItensBinding = LayoutGenericoBinding.inflate(
                (contexto.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater), parent, false)
            val itensLayoutHolder = ItensLayoutHolder(layoutItensBinding.nomeTv, layoutItensBinding.descricaoTv)

            layoutItensBinding.root.tag = itensLayoutHolder

            layoutItensBinding.root
        }

        //Preenchendo ou atualizando a view
        val item: Itens = itensList[position]
        val holder = itemLayout.tag as ItensLayoutHolder
        holder.nomeTv.text = item.nomeLista
        holder.descricaoTv.text = item.descLista

        return itemLayout
    }

    private data class ItensLayoutHolder(
        val nomeTv : TextView,
        val descricaoTv : TextView
    ){

    }
}