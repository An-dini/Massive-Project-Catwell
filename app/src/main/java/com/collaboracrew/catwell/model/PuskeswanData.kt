package com.collaboracrew.catwell.model


var pswlist = mutableListOf<PuskeswanData>()

val PSW_ID_EXTRA = "pswExtra"
data class PuskeswanData(
    val image: Int,
    val namapsw: String,
    val deskripsi: String,
    val id: Int? = pswlist.size
)
