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
    private val geralList: MutableList<Geral> = mutableListOf()

    //Adapter
    private val geralAdapter: GeralRvAdapter by lazy {
        GeralRvAdapter(this, geralList)
    }

    //Activity Result Launcher
    private lateinit var geralListaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarGeralListaActivityResultLauncher: ActivityResultLauncher<Intent>

    //LayoutManager
    private val geralLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGeralListaBinding.root)

        //Associa view com Adapter e com o LayoutManager
        activityGeralListaBinding.itensRv.adapter = geralAdapter
        activityGeralListaBinding.itensRv.layoutManager = geralLayoutManager

        geralListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    resultado.data?.getParcelableExtra<Geral>(EXTRA_GERAL)?.apply {
                        geralList.add(this)
                        geralAdapter.notifyDataSetChanged()
                    }
                }

            }
        editarGeralListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_GERAL, -1)
                    resultado.data?.getParcelableExtra<Geral>(EXTRA_GERAL)?.apply {
                        if (posicao != -1 && posicao != null) {
                            geralList[posicao] = this
                            geralAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        //Tratando o evento do Fab
        activityGeralListaBinding.adicionarItemFab.setOnClickListener {
            geralListaActivityResultLauncher.launch(Intent(this, GeralActivity::class.java))
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
        val posicao = geralAdapter.posicao

        return when (item.itemId) {
            R.id.detalhesItemMi -> {
                //Consultar detalhes
                val item = geralList[posicao]
                val consultarGeralIntent = Intent(this, GeralActivity::class.java)
                consultarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
                startActivity(consultarGeralIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Geral da Lista
                val item = geralList[posicao]
                val editarGeralIntent = Intent(this, GeralActivity::class.java)
                editarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
                editarGeralIntent.putExtra(GeralListaActivity.EXTRA_POSICAO_GERAL, posicao)
                editarGeralListaActivityResultLauncher.launch(editarGeralIntent)
                true
            }
            R.id.removerItemMi -> {
                //Remover Item da Lista
                geralList.removeAt(posicao)
                geralAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun OnItemClick(posicao: Int) {
        val item = geralList[posicao]
        val consultarGeralIntent = Intent(this, GeralActivity::class.java)
        consultarGeralIntent.putExtra(GeralListaActivity.EXTRA_GERAL, item)
        startActivity(consultarGeralIntent)
    }


}