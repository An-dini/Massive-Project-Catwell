package com.collaboracrew.catwell.api

import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.LoginModel
import com.collaboracrew.catwell.model.SubmitModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("Email_User") emailUser: String,
        @Field("PW_User") passUser: String,
        @Field("Tipe_Pengguna") tipePengguna: String,
    ): Call<LoginModel>

    @FormUrlEncoded
    @POST("register.php")
    fun create(
        @Field("Nama_User") namaUser: String,
        @Field("Email_User") emailUser: String,
        @Field("PW_User") passUser: String,
        @Field("Gender_User") genderUser: String,
        @Field("Tipe_Pengguna") tipePengguna: String,
    ): Call<SubmitModel>

    @FormUrlEncoded
    @POST("detail_dokter.php")
    fun detailDokter(
        @Field("ID_Dokter") ID_Dokter: String
    ): Call<DoctorModel>
    @GET("data_dokter.php")
    fun dataDokter(): Call<DoctorModel>
}