package br.edu.ifsp.scl.sdm.listpad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasListaBinding
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityGeralListaBinding
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityItemBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra
import br.edu.ifsp.scl.sdm.listpad.model.Geral
import br.edu.ifsp.scl.sdm.listpad.model.Itens

class GeralListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
        const val EXTRA_POSICAO = "POSICAO_EXTRA"
    }

    private val activityGeralListaBinding: ActivityGeralListaBinding by lazy{
        ActivityGeralListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val geralList: MutableList<Geral> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geral_lista)
    }

    override fun OnItemClick(posicao: Int) {
        TODO("Not yet implemented")
    }
}