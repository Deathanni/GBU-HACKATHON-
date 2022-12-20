package com.example.chhatrahapplication.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Basic"+ Base64.encodeToString("User:123456".toByteArray(),Base64.NO_WRAP)
    private const val BASE_URL = "http://95.216.198.59:8080/"
    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", AUTH)
                    .method(original.method(),original.body())

                val request = requestBuilder.build()
                         chain.proceed(request)
            }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }


}