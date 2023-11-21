package com.collaboracrew.catwell.model

var vetList = mutableListOf<VetData>()

val VET_ID_EXTRA = "vetExtra"
data class VetData(
    val image: Int,
    val namavet: String,
    val deskripsi: String,
    val id: Int? = vetList.size

)
