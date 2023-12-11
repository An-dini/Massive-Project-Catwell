package com.collaboracrew.catwell.api

import com.collaboracrew.catwell.model.LoginModel
import com.collaboracrew.catwell.model.SubmitModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email_user") emailUser: String,
        @Field("pass_user") passUser: String
    ): Call<LoginModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("nama_user") namaUser: String,
        @Field("email_user") emailUser: String,
        @Field("pass_user") passUser: String,
        @Field("gender_user") genderUser: String,
    ): Call<SubmitModel>
}