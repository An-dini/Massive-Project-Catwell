package com.collaboracrew.catwell.model

data class Vet_Model (var result: ArrayList<Result> ){
    data class Result (
        var ID_Vet: String,
        var Nama_Vet: String,
        var Alamat:String,
        var Waktu_Buka:String,
        var No_Vet:String,
        var Img_Vet:String,
    )
}
