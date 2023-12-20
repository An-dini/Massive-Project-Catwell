package com.collaboracrew.catwell.model

import java.io.Serializable

data class User(
    val user: List<Data>
){
    data class Data(val ID_User: String,
                    val uuid: String?,
                    val Nama_User: String?,
                    val Email_User: String?,
                    val PW_User: String?,
                    val Gender_User: String?,
                    val Tipe_Pengguna: String?,
                    val No_User: String?,
                    val Foto_User: String?,

                    ) : Serializable
}

