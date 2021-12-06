package br.edu.ifsp.scl.sdm.listpad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.listpad.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.listpad.model.Itens

class MainActivity : AppCompatActivity() {

    companion object Extras {
        const val EXTRA_ITEM = "ITEM_EXTRA"
    }

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    // Data source
    private val itensList: MutableList<Itens> = mutableListOf()

    //Adapter
    private val itensAdapter: ArrayAdapter<String> by lazy{

        val itensStringList = mutableListOf<String>()
        itensList.forEach { itens -> itensStringList.add(itens.toString()) }
        ArrayAdapter(this, android.R.layout.simple_list_item_1, itensStringList)
    }

    //Activity Result Launcher
    private lateinit var itemActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.itensLv)

        //Iniciando a lista de listas
        inicializarListaItens();

        activityMainBinding.itensLv.adapter = itensAdapter

        itemActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            resultado -> if (resultado.resultCode == RESULT_OK){
                resultado.data?.getParcelableExtra<Itens>(EXTRA_ITEM)?.apply {
                    itensList.add(this)
                    itensAdapter.add(this.toString())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
            R.id.adicionarItemMi -> {

                itemActivityResultLauncher.launch(Intent(this, ItemActivity::class.java))
                true
            }
            else -> {
                false
            }
    }

    private fun inicializarListaItens(){
        for (indice in 1..10){
            itensList.add(
                Itens(
                    "Tipo: $indice",
                    "Descrição $indice"
                )
            )
        }
    }

}