package br.edu.ifsp.scl.sdm.listpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasListaBinding
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityCompromissosListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra

class CompromissosListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
        const val EXTRA_POSICAO = "POSICAO_EXTRA"
    }

    private val activityComproissosListaBinding: ActivityCompromissosListaBinding by lazy{
        ActivityCompromissosListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val compromissosList: MutableList<Compra> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compromissos_lista)
    }

    override fun OnItemClick(posicao: Int) {
        TODO("Not yet implemented")
    }
}