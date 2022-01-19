package br.edu.ifsp.scl.sdm.listpad

//Trata eventos de clique na main Activity e sera usada no Adapter para tratar eventos de clique na celula
interface OnItemClickListener {
    fun OnItemClick(posicao : Int)
}