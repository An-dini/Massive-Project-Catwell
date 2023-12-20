package com.collaboracrew.catwell.model

data class ProductRecommendationModel(
    val produk: List<Data>
){
    data class Data(val id: Int?,
                    val nama_produk: String?,
                    val kategori: String?,
                    val foto_produk: String?,
                    val link_produk: String?)
}