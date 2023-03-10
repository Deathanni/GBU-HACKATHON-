package com.example.chhatrahapplication.api

import com.example.chhatrahapplication.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {

    fun loginUser(userData: User, onResult: (User?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.logIn(userData).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<User>, response: Response<User>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun loginAdmin(adminData: Warden, onResult: (Warden?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.adminLogIn(adminData).enqueue(
            object : Callback<Warden> {
                override fun onFailure(call: Call<Warden>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<Warden>, response: Response<Warden>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun addUser(student: DefaultResponse, onResult: (DefaultResponse?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.signIn(student).enqueue(
            object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
    fun addQueries(query: AddQuery, onResult: (AddQuery?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.addQuery(query).enqueue(
            object : Callback<AddQuery> {
                override fun onFailure(call: Call<AddQuery>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<AddQuery>, response: Response<AddQuery>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
    fun seeQuery(query: QueriesOnList, onResult: (QueriesOnList?) -> Unit){
        val retrofit = RetrofitClient.buildService(Api::class.java)
        retrofit.seeQuery(query).enqueue(
            object : Callback<QueriesOnList> {
                override fun onFailure(call: Call<QueriesOnList>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<QueriesOnList>, response: Response<QueriesOnList>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

}