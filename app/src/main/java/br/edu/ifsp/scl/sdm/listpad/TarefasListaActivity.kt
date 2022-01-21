package br.edu.ifsp.scl.sdm.listpad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.sdm.listpad.adapter.TarefasRvAdapter
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityTarefasListaBinding
import br.edu.ifsp.scl.sdm.listpad.model.Tarefa

class TarefasListaActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_TAREFA = "ITEM_EXTRA"
        const val EXTRA_POSICAO_TAREFA = "POSICAO_EXTRA"
    }

    private val activityTarefasListaBinding: ActivityTarefasListaBinding by lazy {
        ActivityTarefasListaBinding.inflate(layoutInflater)
    }

    // Data Source
    private val tarefaList: MutableList<Tarefa> = mutableListOf()

    //Adapter
    private val tarefasAdapter: TarefasRvAdapter by lazy {
        TarefasRvAdapter(this, tarefaList)
    }

    //Activity Result Launcher
    private lateinit var tarefaListaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarTarefasListaActivityResultLauncher: ActivityResultLauncher<Intent>

    //LayoutManager
    private val tarefasLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityTarefasListaBinding.root)

        //Associa view com Adapter e com o LayoutManager
        activityTarefasListaBinding.itensRv.adapter = tarefasAdapter
        activityTarefasListaBinding.itensRv.layoutManager = tarefasLayoutManager

        tarefaListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    resultado.data?.getParcelableExtra<Tarefa>(EXTRA_TAREFA)?.apply {
                        tarefaList.add(this)
                        tarefasAdapter.notifyDataSetChanged()
                    }
                }
        }
        editarTarefasListaActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_TAREFA, -1)
                    resultado.data?.getParcelableExtra<Tarefa>(EXTRA_TAREFA)?.apply {
                        if (posicao != -1 && posicao != null) {
                            tarefaList[posicao] = this
                            tarefasAdapter.notifyDataSetChanged()
                        }
                    }
                }
        }
        //Tratando o evento do Fab
        activityTarefasListaBinding.adicionarItemFab.setOnClickListener {
            tarefaListaActivityResultLauncher.launch(Intent(this, TarefaActivity::class.java))
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
        val posicao = tarefasAdapter.posicao

        return when (item.itemId) {
            R.id.detalhesItemMi -> {
                //Consultar detalhes
                val item = tarefaList[posicao]
                val consultarTarefaIntent = Intent(this, TarefaActivity::class.java)
                consultarTarefaIntent.putExtra(TarefasListaActivity.EXTRA_TAREFA, item)
                startActivity(consultarTarefaIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Tarefa da Lista
                val item = tarefaList[posicao]
                val editarTarefaIntent = Intent(this, TarefaActivity::class.java)
                editarTarefaIntent.putExtra(TarefasListaActivity.EXTRA_TAREFA, item)
                editarTarefaIntent.putExtra(TarefasListaActivity.EXTRA_POSICAO_TAREFA, posicao)
                editarTarefasListaActivityResultLauncher.launch(editarTarefaIntent)
                true
            }
            R.id.removerItemMi -> {
                //Remover Item da Lista
                tarefaList.removeAt(posicao)
                tarefasAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun OnItemClick(posicao: Int) {
        val item = tarefaList[posicao]
        val consultarTarefaIntent = Intent(this, TarefaActivity::class.java)
        consultarTarefaIntent.putExtra(TarefasListaActivity.EXTRA_TAREFA, item)
        startActivity(consultarTarefaIntent)
    }


}