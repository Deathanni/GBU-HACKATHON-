package com.example.chhatrahapplication.api

import android.telecom.Call
import com.example.chhatrahapplication.models.DefaultResponse
import com.example.chhatrahapplication.models.LoginResponse
import com.example.chhatrahapplication.models.*
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST( "signin")
    fun signIn(
        @Body adminData: DefaultResponse
    ):retrofit2.Call<DefaultResponse>


    @POST("login")
    @Headers("Content-Type: application/json")
    fun logIn(
        @Body userData: User
    ):retrofit2.Call<User>

    @Headers("Content-Type: application/json")
    @POST("admin/login")
    fun adminLogIn(
        @Body adminData: Warden
    ):retrofit2.Call<Warden>
}