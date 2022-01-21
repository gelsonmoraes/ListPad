package br.edu.ifsp.scl.sdm.listpad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.sdm.listpad.adapter.ComprasRvAdapter
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityComprasListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compra

class ComprasListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_COMPRA = "ITEM_EXTRA"
        const val EXTRA_POSICAO_COMPRA = "POSICAO_EXTRA"
    }

    private val activityComprasListaBinding: ActivityComprasListaBinding by lazy {
        ActivityComprasListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val compraList: MutableList<Compra> = mutableListOf()

    //Adapter
    private val comprasAdapter: ComprasRvAdapter by lazy {
        ComprasRvAdapter(this, compraList)
    }

    //Activity Result Launcher
    private lateinit var compraListaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarComprasListaActivityResultLauncher: ActivityResultLauncher<Intent>

    //LayoutManager
    private val comprasLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityComprasListaBinding.root)

        //Associa view com Adapter e com o LayoutManager
        activityComprasListaBinding.itensRv.adapter = comprasAdapter
        activityComprasListaBinding.itensRv.layoutManager = comprasLayoutManager

        compraListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    resultado.data?.getParcelableExtra<Compra>(EXTRA_COMPRA)?.apply {
                        compraList.add(this)
                        comprasAdapter.notifyDataSetChanged()
                    }
                }

            }
        editarComprasListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_COMPRA, -1)
                    resultado.data?.getParcelableExtra<Compra>(EXTRA_COMPRA)?.apply {
                            if (posicao != -1 && posicao != null) {
                                compraList[posicao] = this
                                comprasAdapter.notifyDataSetChanged()
                            }
                    }
                }
        }
        //Tratando o evento do Fab
        activityComprasListaBinding.adicionarItemFab.setOnClickListener {
            compraListaActivityResultLauncher.launch(Intent(this, ItemActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.sairMi -> {
            finish()
            true
        }
        else -> {
            false
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = comprasAdapter.posicao

        return when (item.itemId) {
            R.id.detalhesItemMi -> {
                //Consultar detalhes
                val item = compraList[posicao]
                val consultarCompraIntent = Intent(this, ItemActivity::class.java)
                consultarCompraIntent.putExtra(ComprasListaActivity.EXTRA_COMPRA, item)
                startActivity(consultarCompraIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Compra da Lista
                val item = compraList[posicao]
                val editarCompraIntent = Intent(this, ItemActivity::class.java)
                editarCompraIntent.putExtra(ComprasListaActivity.EXTRA_COMPRA, item)
                editarCompraIntent.putExtra(ComprasListaActivity.EXTRA_POSICAO_COMPRA, posicao)
                editarComprasListaActivityResultLauncher.launch(editarCompraIntent)
                true
            }
            R.id.removerItemMi -> {
                //Remover Item da Lista
                compraList.removeAt(posicao)
                comprasAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun OnItemClick(posicao: Int) {
        val item = compraList[posicao]
        val consultarCompraIntent = Intent(this, ItemActivity::class.java)
        consultarCompraIntent.putExtra(ComprasListaActivity.EXTRA_COMPRA, item)
        startActivity(consultarCompraIntent)
    }


}


