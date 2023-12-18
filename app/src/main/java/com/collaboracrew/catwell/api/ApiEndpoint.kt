package com.collaboracrew.catwell.api

import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.LoginModel
import com.collaboracrew.catwell.model.SubmitModel
import com.collaboracrew.catwell.model.TransactionModel
import com.collaboracrew.catwell.model.User
import retrofit2.Call
import retrofit2.http.Body
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

    @GET("data_user.php")
    fun listUser(): Call<User>

    @FormUrlEncoded
    @POST("data_user.php")
    fun dataUser(
        @Field("Email_User") Email_User: String,
    ): Call<User>

    @FormUrlEncoded
    @POST("input_pembayaran.php")
    fun inputPembayaran(
        @Field("order_id") order_id: String,
        @Field("Email_User") Email_User: String,
        @Field("ID_Dokter") ID_Dokter: String,
        @Field("tipe_konsultasi") tipe_konsultasi: String
    ): Call<TransactionModel>
}