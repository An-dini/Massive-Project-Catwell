package com.collaboracrew.catwell.model

data class TransactionModel(
    val order_id: String,
    val transaction_id: String,
    val ID_User: String,
    val ID_Dokter: String,
    val amount: String?,
    val payment_type: String,
    val status_transaksi: String,
    val tansaction_time: String,
    val tipe_konsultasi: String
)
