package com.collaboracrew.catwell.model

data class Puskeswan_Model(var result:ArrayList<Result>){
    data class Result(
        var ID_Puskeswan: String,
        var Nama_Puskeswan: String,
        var Alamat:String,
        var Waktu_Buka:String,
        var No_Puskeswan:String,
        var Img_Puskeswan:String,
    )
}
