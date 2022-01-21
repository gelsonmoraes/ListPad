package br.edu.ifsp.scl.sdm.listpad.db
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.audiofx.AudioEffect
import br.edu.ifsp.scl.sdm.listpad.model.*
import br.edu.ifsp.scl.sdm.listpad.model.Itens

open class DatabaseHandler (
    ctx: Context): SQLiteOpenHelper(ctx, DB_NAME,null, DB_VERSION) {


    companion object {
        private val DB_NAME = "lista.db"
        private val DB_VERSION = 1
        private val ID = "ID"
        private val NOME = "NOME"
        private val DESC = "DESCRICAO"

    }

    override fun onCreate(db: SQLiteDatabase?): Unit {
        val CREATE_TABLE =
            "CREATE TABLE TB_ITENS ($ID PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESC TEXT)" +
            "CREATE TABLE TB_COMPRAS ($ID PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESC TEXT)" +
            "CREATE TABLE TB_COMPROMISSOS ($ID PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESC TEXT)" +
            "CREATE TABLE TB_GERAL ($ID PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESC TEXT)" +
            "CREATE TABLE TB_TAREFAS ($ID PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESC TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        //Inserindo dados no banco
        fun insertItens(item: Itens): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, item.id)
            values.put(NOME, item.nomeLista)
            values.put(DESC, item.descLista)
            val result = db.insert("TB_ITENS", null, values)
            db.close()
            return result
        }

        fun insertCompras(compra: Compra): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, compra.id)
            values.put(NOME, compra.nomeCompra)
            values.put(DESC, compra.nomeCompra)
            val result = db.insert("TB_COMPRAS", null, values)
            db.close()
            return result
        }

        fun insertCompromissos(compromisso: Compromisso): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, compromisso.id)
            values.put(NOME, compromisso.nomeCompromisso)
            values.put(DESC, compromisso.descCompromisso)
            val result = db.insert("TB_COMPROMISSOS", null, values)
            db.close()
            return result
        }

        fun insertGeral(geral: Geral): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, geral.id)
            values.put(NOME, geral.nomeGeral)
            values.put(DESC, geral.nomeGeral)
            val result = db.insert("TB_GERAL", null, values)
            db.close()
            return result
        }

        fun insertTarefas(tarefa: Tarefa): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, tarefa.id)
            values.put(NOME, tarefa.nomeTarefa)
            values.put(DESC, tarefa.descTarefa)
            val result = db.insert("TB_TAREFAS", null, values)
            db.close()
            return result
        }

        // Atualizando dados no banco

        fun updateItens(item: Itens): Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, item.id)
            values.put(NOME, item.nomeLista)
            values.put(DESC, item.descLista)
            val result = db.update("TB_ITENS",values,"$ID=?", arrayOf(item.id.toString()))
            db.close()
            return result
        }

        fun updateCompras(compra: Compra): Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, compra.id)
            values.put(NOME, compra.nomeCompra)
            values.put(DESC, compra.descCompra)
            val result = db.update("TB_COMPRAS",values,"$ID=?", arrayOf(compra.id.toString()))
            db.close()
            return result
        }

        fun updateCompromissos(compromisso: Compromisso): Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, compromisso.id)
            values.put(NOME, compromisso.nomeCompromisso)
            values.put(DESC, compromisso.descCompromisso)
            val result = db.update("TB_COMPROMISSOS", values, "$ID=?", arrayOf(compromisso.id.toString()))
            db.close()
            return result
        }

        fun updateGeral(geral: Geral): Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, geral.id)
            values.put(NOME, geral.nomeGeral)
            values.put(DESC, geral.nomeGeral)
            val result = db.update("TB_GERAL", values, "$ID=?", arrayOf(geral.toString()))
            db.close()
            return result
        }

        fun updateTarefas(tarefa: Tarefa): Int {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID, tarefa.id)
            values.put(NOME, tarefa.nomeTarefa)
            values.put(DESC, tarefa.descTarefa)
            val result = db.update("TB_TAREFAS", values, "$ID=?", arrayOf(tarefa.toString()))
            db.close()
            return result
        }

        //Excluindo do Banco de dados
        fun deleteItens(item: Itens){
            val db = this.writableDatabase
            val result = db.delete("TB_ITENS", "$ID=?", arrayOf(item.toString()))
        }

        fun deleteCompras(compra: Compra){
            val db = this.writableDatabase
            val result = db.delete("TB_COMPRAS", "$ID=?", arrayOf(compra.toString()))
        }

        fun deleteCompromissos(compromisso: Compromisso){
            val db = this.writableDatabase
            val result = db.delete("TB_COMPROMISSOS", "$ID=?", arrayOf(compromisso.toString()))
        }
        fun deleteGeral(geral: Geral){
            val db = this.writableDatabase
            val result = db.delete("TB_GERAL", "$ID=?", arrayOf(geral.toString()))
        }

        fun deleteTarefas(tarefa: Tarefa){
            val db = this.writableDatabase
            val result = db.delete("TB_TAREFAS", "$ID=?", arrayOf(tarefa.toString()))
        }


    }

}

