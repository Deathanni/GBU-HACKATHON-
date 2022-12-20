package com.example.chhatrahapplication.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("roll_no") val roll_no: String?,
    @SerializedName("password") val password:String?,
    @SerializedName("token") val token:String?,
)