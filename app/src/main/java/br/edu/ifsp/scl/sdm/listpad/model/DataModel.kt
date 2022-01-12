


package br.edu.ifsp.scl.sdm.listpad.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Itens(
    val nomeLista: String,
    val descLista: String
) : Parcelable

@Parcelize
data class Tarefa(
    val nomeTarefa : String,
    val descTarefa: String,
    val prioridadeTarefa : Boolean
) : Parcelable

@Parcelize
data class Compra(
    val nomeCompra : String,
    val descCompra : String,
    val prioridadeCompra : Boolean
) : Parcelable

@Parcelize
data class Compromisso(
    val nomeCompromisso : String,
    val descCompromisso : String,
    val prioridadeCompromisso : Boolean
) : Parcelable

@Parcelize
data class Geral(
    val nomeGeral : String,
    val descGeral: String,
    val prioridadeGeral : Boolean
) : Parcelable