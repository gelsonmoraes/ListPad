package br.edu.ifsp.scl.sdm.listpad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.sdm.listpad.adapter.ItensRvAdapter
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.listpad.model.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
        const val EXTRA_POSICAO = "POSICAO_EXTRA"
    }

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    // Data source
    private val itensList: MutableList<Itens> = mutableListOf()
    private val compromissoList: MutableList<Compromisso> = mutableListOf()
    private val geralList: MutableList<Geral> = mutableListOf()
    private val tarefaList: MutableList<Tarefa> = mutableListOf()



    //Adapter
    private val itensAdapter: ItensRvAdapter by lazy{
        ItensRvAdapter(this, itensList)
    }

    //LayoutManager
    private val itensLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    //Activity Result Launcher
    private lateinit var itemActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarItemActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //Iniciando a lista de itens
        inicializarListaItens();

        //Associa view com Adapter e com o LayoutManager
        activityMainBinding.itensRv.adapter = itensAdapter
        activityMainBinding.itensRv.layoutManager = itensLayoutManager


        itemActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            resultado -> if (resultado.resultCode == RESULT_OK){
                resultado.data?.getParcelableExtra<Itens>(EXTRA_ITEM)?.apply {
                    itensList.add(this)
                    itensAdapter.notifyDataSetChanged()
                }
            }
        }

        editarItemActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                resultado ->
            if (resultado.resultCode == RESULT_OK){
                val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO, -1)
                resultado.data?.getParcelableExtra<Itens>(EXTRA_ITEM)?.apply {
                    if(posicao != -1 && posicao != null){
                        itensList[posicao] = this
                        itensAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
            R.id.sairMi -> {
                finish()
                true
            }
            else -> {
                false
            }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = itensAdapter.posicao

        return when(item.itemId){
            R.id.acessarItemMi -> {
               //Acessar a lista selecionada
                val item = itensList[posicao]
                var acessarItemIntent = Intent()
                when(posicao) {
                    0 -> acessarItemIntent = Intent(this, ComprasListaActivity::class.java)
                    1 -> acessarItemIntent = Intent(this, CompromissosListaActivity::class.java)
                    2 -> acessarItemIntent = Intent(this, GeralListaActivity::class.java)
                    3 -> acessarItemIntent = Intent(this, TarefasListaActivity::class.java)
                    else -> { "Invalid activity" }
                }
                acessarItemIntent.putExtra(EXTRA_ITEM, item)
                startActivity(acessarItemIntent)
                true
            }
            R.id.detalhesItemMi -> {
                //Consultar detalhes
                val item = itensList[posicao]
                val consultarItemIntent = Intent(this, ItemActivity::class.java)
                consultarItemIntent.putExtra(EXTRA_ITEM, item)
                startActivity(consultarItemIntent)
                true
            }
            R.id.editarItemMi -> {
                //Editar Item da Lista
                val item = itensList[posicao]
                val editarItemIntent = Intent(this, ItemActivity::class.java)
                editarItemIntent.putExtra(EXTRA_ITEM, item)
                editarItemIntent.putExtra(EXTRA_POSICAO, posicao)
                editarItemActivityResultLauncher.launch(editarItemIntent)
                true
            }

            else -> {false}
        }
    }

    private fun inicializarListaItens(){

        itensList.add(
            Itens(
            "Compras",
                "Nesta lista você coloca o que precisa comprar. ",
        )

        )
        itensList.add(
            Itens(
                "Compromisso",
                "Nesta lista você coloca seus próximos eventos.",
            )
        )
        itensList.add(
            Itens(
                "Geral",
                "Lista de elementos em geral, aqui você pode listar o que quiser.",
            )
        )
        itensList.add(
            Itens(
                "Tarefa",
                "Aqui você tem sua lista de afazeres.",
            )
        )
    }

    override fun OnItemClick(posicao: Int) {
        val item = itensList[posicao]
        var acessarItemIntent = Intent()
        when(posicao) {
            0 -> acessarItemIntent = Intent(this, ComprasListaActivity::class.java)
            1 -> acessarItemIntent = Intent(this, CompromissosListaActivity::class.java)
            2 -> acessarItemIntent = Intent(this, GeralListaActivity::class.java)
            3 -> acessarItemIntent = Intent(this, TarefasListaActivity::class.java)
            else -> { "Invalid activity" }
        }
        acessarItemIntent.putExtra(EXTRA_ITEM, item)
        startActivity(acessarItemIntent)
    }
}