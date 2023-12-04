package com.collaboracrew.catwell.model

data class UserModel(
    val id: String?,
    val nama_lengkap: String,
    val jenis_kelamin: String,
    val email_user: String,
    val password: String,
    val tipe_pengguna: String
)
