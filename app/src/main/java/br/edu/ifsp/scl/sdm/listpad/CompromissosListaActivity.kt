package br.edu.ifsp.scl.sdm.listpad
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.sdm.listpad.adapter.CompromissosRvAdapter
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityCompromissosListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Compromisso

class CompromissosListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_COMPROMISSO = "ITEM_EXTRA"
        const val EXTRA_POSICAO_COMPROMISSO = "POSICAO_EXTRA"
    }

    private val activityCompromissosListaBinding: ActivityCompromissosListaBinding by lazy {
        ActivityCompromissosListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val compromissoList: MutableList<Compromisso> = mutableListOf()

    //Adapter
    private val compromissoAdapter: CompromissosRvAdapter by lazy {
        CompromissosRvAdapter(this, compromissoList)
    }

    //Activity Result Launcher
    private lateinit var compromissoListaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarCompromissosListaActivityResultLauncher: ActivityResultLauncher<Intent>

    //LayoutManager
    private val compromissoLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityCompromissosListaBinding.root)

        //Associa view com Adapter e com o LayoutManager
        activityCompromissosListaBinding.itensRv.adapter = compromissoAdapter
        activityCompromissosListaBinding.itensRv.layoutManager = compromissoLayoutManager

        compromissoListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    resultado.data?.getParcelableExtra<Compromisso>(CompromissosListaActivity.EXTRA_COMPROMISSO)?.apply {
                        compromissoList.add(this)
                        compromissoAdapter.notifyDataSetChanged()
                    }
                }

            }
        editarCompromissosListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(CompromissosListaActivity.EXTRA_POSICAO_COMPROMISSO, -1)
                    resultado.data?.getParcelableExtra<Compromisso>(CompromissosListaActivity.EXTRA_COMPROMISSO)?.apply {
                        if (posicao != -1 && posicao != null) {
                            compromissoList[posicao] = this
                            compromissoAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        //Tratando o evento do Fab
        activityCompromissosListaBinding.adicionarItemFab.setOnClickListener{
            compromissoListaActivityResultLauncher.launch(Intent(this, CompromissoActivity::class.java))
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
        val posicao = compromissoAdapter.posicao

        return when (item.itemId) {
            R.id.detalhesItemMi -> {
                //Consultar detalhes
                val item = compromissoList[posicao]
                val consultarCompromissoIntent = Intent(this, CompromissoActivity::class.java)
                consultarCompromissoIntent.putExtra(CompromissosListaActivity.EXTRA_COMPROMISSO, item)
                startActivity(consultarCompromissoIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Compromisso da Lista
                val item = compromissoList[posicao]
                val editarCompromissoIntent = Intent(this, CompromissoActivity::class.java)
                editarCompromissoIntent.putExtra(CompromissosListaActivity.EXTRA_COMPROMISSO, item)
                editarCompromissoIntent.putExtra(CompromissosListaActivity.EXTRA_POSICAO_COMPROMISSO, posicao)
                editarCompromissosListaActivityResultLauncher.launch(editarCompromissoIntent)
                true
            }
            R.id.removerItemMi -> {
                //Remover Item da Lista
                compromissoList.removeAt(posicao)
                compromissoAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun OnItemClick(posicao: Int) {
        val item = compromissoList[posicao]
        val consultarCompromissoIntent = Intent(this, CompromissoActivity::class.java)
        consultarCompromissoIntent.putExtra(CompromissosListaActivity.EXTRA_COMPROMISSO, item)
        startActivity(consultarCompromissoIntent)
    }


}