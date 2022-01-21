package br.edu.ifsp.scl.sdm.listpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra

class ComprasListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
        const val EXTRA_POSICAO = "POSICAO_EXTRA"
    }

    private val activityComprasListaBinding: ActivityComprasListaBinding by lazy{
        ActivityComprasListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val compraList: MutableList<Compra> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras_lista)
    }

    override fun OnItemClick(posicao: Int) {
        TODO("Not yet implemented")
    }
}