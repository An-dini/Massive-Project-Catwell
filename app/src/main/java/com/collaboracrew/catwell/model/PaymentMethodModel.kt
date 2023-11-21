package com.collaboracrew.catwell.model

data class PaymentMethodModel(
    val logoResource: Int,
    val mitraName: String,
    var isVisible: Boolean = true
)

