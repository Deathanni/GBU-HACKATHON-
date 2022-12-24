package com.example.chhatrahapplication

import android.content.Context
import com.example.chhatrahapplication.models.User
import com.example.chhatrahapplication.activities.AddingQuery
import com.example.chhatrahapplication.models.QueriesOnList


class SharedPrefManager private constructor(private val mCtx: Context) {

                    val isLoggedIn: Boolean
                        get() {
                            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)

                            return !sharedPreferences.getString("token", "").isNullOrEmpty()
                        }

                    val reqToken: String?
                        get() {
                            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                            return sharedPreferences.getString("token", "")
                        }
                    val reqRoll: String?
                           get() {
                               val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
                          return sharedPreferences.getString("roll_no", "")
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
    val complaint: QueriesOnList
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
            return QueriesOnList(
                uid = "",
                roll_no = "",
                token ="",
                complaint_text = "",
                complaint_text_title = "",
                hostel_code = "",
                query_resolved = ""
            )
        }


    fun saveQueries( uid : String?,
                  roll_no : String?,
                  token : String?,
                  complaint_text: String?,
                  complaint_text_title : String?,
                  hostel_code : String?,
                  query_resolved: String?) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_password, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("uid", uid)
        editor.putString("token", token)
        editor.putString("roll_no", roll_no)
        editor.putString("complaint_text", token)
        editor.putString("complaint_text_title", roll_no)
        editor.putString("hostel_code", token)
        editor.putString("query_resolved", roll_no)
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