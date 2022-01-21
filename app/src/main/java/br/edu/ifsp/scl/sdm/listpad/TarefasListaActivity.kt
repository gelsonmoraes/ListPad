package br.edu.ifsp.scl.sdm.listpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasListaBinding
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityTarefasListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra

class TarefasListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
        const val EXTRA_POSICAO = "POSICAO_EXTRA"
    }

    private val activityTarefasListaBinding: ActivityTarefasListaBinding by lazy{
        ActivityTarefasListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val tarefaList: MutableList<Compra> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefas_lista)
    }

    override fun OnItemClick(posicao: Int) {
        TODO("Not yet implemented")
    }
}