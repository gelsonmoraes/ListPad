


package br.edu.ifsp.scl.sdm.listpad.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Itens(
    val id: Int?,
    val nomeLista: String,
    val descLista: String
) : Parcelable

@Parcelize
data class Tarefa(
    val id: Int?,
    val nomeTarefa : String,
    val descTarefa: String,
) : Parcelable

@Parcelize
data class Compra(
    val id: Int?,
    val nomeCompra : String,
    val descCompra : String,
) : Parcelable

@Parcelize
data class Compromisso(
    val id: Int?,
    val nomeCompromisso : String,
    val descCompromisso : String,
) : Parcelable

@Parcelize
data class Geral(
    val id: Int?,
    val nomeGeral : String,
    val descGeral: String,
) : Parcelable

