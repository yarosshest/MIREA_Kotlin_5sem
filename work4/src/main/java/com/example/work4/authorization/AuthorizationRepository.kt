package com.example.work4.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.work4.api.Api
import com.example.work4.api.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationRepository {
    val api: Api = RetrofitHelper.getInstance().create(Api::class.java)
    private val loginStatus = MutableLiveData<Boolean>()

    fun tryLoginIn(login: String, password : String): LiveData<Boolean> {
        val call = api.login(
            login = login,
            password = password
        )

        call.enqueue(object : Callback<Map<String, Int>> {
            override fun onResponse(
                call: Call<Map<String, Int>>,
                response: Response<Map<String, Int>>
            ) {
                if (response.isSuccessful) {
                    loginStatus.value = true
                }else{
                    if (response.code() == 404)
                        loginStatus.value = false
                }
            }

            override fun onFailure(call: Call<Map<String, Int>>, t: Throwable) {
                println("Network Error :: " + t.localizedMessage)
                loginStatus.value = false
            }

        })
        return loginStatus
    }
}