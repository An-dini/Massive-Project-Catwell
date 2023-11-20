package com.collaboracrew.catwell.model

var transactionList = mutableListOf<RiwayatModel>()

val TRANSACTION_ID_EXTRA = "transactionExtra"

data class RiwayatModel(
    val cover: Int,
    val doctor: String,
    val instance: String,
    val rating: Float,
    val date: String,
    val time: String,
    val price: Int,
    val paymentMethod: String,
    val id: Int? = transactionList.size
)

