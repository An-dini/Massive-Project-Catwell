package com.collaboracrew.catwell.api

import com.collaboracrew.catwell.model.LoginModel
import com.collaboracrew.catwell.model.Puskeswan_Model
import com.collaboracrew.catwell.model.SubmitModel
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
