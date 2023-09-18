package com.example.work4.model.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.work4.api.Api
import com.example.work4.api.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserAlreadyExistsException(message: String = "User already exists") : Exception(message)
class UserNotFoundException(message: String = "User not found") : Exception(message)

class UserRepository(private val userDao: UserDao) {

    private val allUsers = MutableLiveData<List<User>>()
    private val api = RetrofitHelper.getInstance().create(Api::class.java)

    fun getAllUsers(): LiveData<List<User>> {
        allUsers.value = userDao.getAllUsers()
        return allUsers
    }

    fun registerUser(login : String, pass :String): Boolean {
        val response = api.register(
            login = login,
            password = pass
        ).execute()

        if (response.isSuccessful){
            userDao.insertUser(User(login = login, password = pass))
            return true
        } else {
            if (response.code() == 405){
                throw UserAlreadyExistsException()
            }
        }

        return false
    }

    fun loginUser(login : String, pass :String): Boolean {
        val response = api.login(
            login = login,
            password = pass
        ).execute()

        if (response.isSuccessful){
            return true
        } else {
            if (response.code() == 404){
                throw UserNotFoundException()
            }
        }
        return false
    }

}