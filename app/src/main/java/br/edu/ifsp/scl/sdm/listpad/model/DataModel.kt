


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
) : Parcelable

@Parcelize
data class Compra(
    val nomeCompra : String,
    val descCompra : String,
) : Parcelable

@Parcelize
data class Compromisso(
    val nomeCompromisso : String,
    val descCompromisso : String,
) : Parcelable

@Parcelize
data class Geral(
    val nomeGeral : String,
    val descGeral: String,
) : Parcelable