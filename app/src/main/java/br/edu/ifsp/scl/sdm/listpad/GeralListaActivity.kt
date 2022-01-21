package br.edu.ifsp.scl.sdm.listpad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.sdm.listpad.adapter.GeralRvAdapter
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityGeralListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Geral

class GeralListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_GERAL = "ITEM_EXTRA"
        const val EXTRA_POSICAO_GERAL = "POSICAO_EXTRA"
    }

    private val activityGeralListaBinding: ActivityGeralListaBinding by lazy {
        ActivityGeralListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val compraList: MutableList<Geral> = mutableListOf()

    //Adapter
    private val comprasAdapter: GeralRvAdapter by lazy {
        GeralRvAdapter(this, compraList)
    }

    //Activity Result Launcher
    private lateinit var compraListaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarGeralListaActivityResultLauncher: ActivityResultLauncher<Intent>

    //LayoutManager
    private val comprasLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGeralListaBinding.root)

        //Associa view com Adapter e com o LayoutManager
        activityGeralListaBinding.itensRv.adapter = comprasAdapter
        activityGeralListaBinding.itensRv.layoutManager = comprasLayoutManager

        compraListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    resultado.data?.getParcelableExtra<Geral>(EXTRA_GERAL)?.apply {
                        compraList.add(this)
                        comprasAdapter.notifyDataSetChanged()
                    }
                }

            }
        editarGeralListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_GERAL, -1)
                    resultado.data?.getParcelableExtra<Geral>(EXTRA_GERAL)?.apply {
                        if (posicao != -1 && posicao != null) {
                            compraList[posicao] = this
                            comprasAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        //Tratando o evento do Fab
        activityGeralListaBinding.adicionarItemFab.setOnClickListener {
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
                val consultarGeralIntent = Intent(this, ItemActivity::class.java)
                consultarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
                startActivity(consultarGeralIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Geral da Lista
                val item = compraList[posicao]
                val editarGeralIntent = Intent(this, ItemActivity::class.java)
                editarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
                editarGeralIntent.putExtra(GeralListaActivity.EXTRA_POSICAO_GERAL, posicao)
                editarGeralListaActivityResultLauncher.launch(editarGeralIntent)
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
        val consultarGeralIntent = Intent(this, ItemActivity::class.java)
        consultarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
        startActivity(consultarGeralIntent)
    }


}