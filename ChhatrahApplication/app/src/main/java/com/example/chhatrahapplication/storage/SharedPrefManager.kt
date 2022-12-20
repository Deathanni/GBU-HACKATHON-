package com.example.chhatrahapplication

import android.content.Context
import com.example.chhatrahapplication.models.User
import android.content.SharedPreferences




class SharedPrefManager private constructor(private val mCtx: Context) {

                    val isLoggedIn: Boolean
                        get() {
                            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                            return !sharedPreferences.getString("token", "").equals("")
                        }

                    val user: User
                        get() {
                            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                            return User(
                                roll_no = "",
                                token = "",
                                password = "")
                        }


                    fun saveUser( token: String,
                                  roll_no: String,
                                  password:String) {

                        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()

                        editor.putString("token", token)
                        editor.putString("roll_no", roll_no)
                        editor.putString("password", password)

                        editor.apply()

                    }

                    fun clear() {
                        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.apply()
                    }

                    companion object {
                        private val SHARED_PREF_password = "my_shared_preff"
                        private var mInstance: SharedPrefManager? = null
                        @Synchronized
                        fun getInstance(mCtx: Context): SharedPrefManager {
                            if (mInstance == null) {
                                mInstance = SharedPrefManager(mCtx)
                            }
                            return mInstance as SharedPrefManager
                        }
                    }

                }