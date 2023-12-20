package com.collaboracrew.catwell.api

import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.LoginModel
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.model.SubmitModel
import com.collaboracrew.catwell.model.TransactionModel
import com.collaboracrew.catwell.model.User
import com.collaboracrew.catwell.model.Puskeswan_Model
import com.collaboracrew.catwell.model.Vet_Model
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @FormUrlEncoded
    @POST("data_user.php")
    fun dataUser(
        @Field("Email_User") Email_User: String,
    ): Call<User>

    @FormUrlEncoded
    @POST("update_user.php")
    fun updateUser(
        @Field("ID_User") ID_User: String,
        @Field("Nama_User") Nama_User: String,
        @Field("Email_User") Email_User: String,
        @Field("No_User") No_User: String,
    ): Call<User>

    @FormUrlEncoded
    @POST("input_pembayaran.php")
    fun inputPembayaran(
        @Field("order_id") order_id: String,
        @Field("Email_User") Email_User: String,
        @Field("ID_Dokter") ID_Dokter: String,
        @Field("tipe_konsultasi") tipe_konsultasi: String
    ): Call<TransactionModel>

    @GET("data_produk.php")
    fun dataProduk(): Call<ProductRecommendationModel>

    @GET("Data_Vet.php")
    fun DataVet(
        @Query("Nama_Vet") Nama_Vet:String,
        @Query("Alamat") Alamat:String,
        @Query("Waktu_Buka") Waktu_Buka:String,
        @Query("No_Vet") No_Vet:String,
        @Query("Img_Vet") Img_Vet:String,
    ): Call<Vet_Model>

    @GET("Data_Puskeswan.php")
    fun DataPuskeswan(
        @Query("Nama_Puskeswan") Nama_Puskeswan:String,
        @Query("Alamat") Alamat: String,
        @Query("Waktu_Buka") Waktu_Buka: String,
        @Query("No_Puskeswan") No_Puskeswan:String,
        @Query("Img_Puskeswan") Img_Puskeswan:String,
    ): Call<Puskeswan_Model>

}
