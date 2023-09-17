package com.example.work4.authorization

import androidx.lifecycle.MutableLiveData
import com.example.work4.app.App
import com.example.work4.model.user.UserDao
import com.example.work4.model.user.UserNotFoundException
import com.example.work4.model.user.UserRepository
import javax.inject.Inject

data class RegisterStatus(
    var status : Boolean = false,
    var registerError : String = ""
)
class RegisterRepository@Inject constructor(
    private val userDao: UserDao
) {
    private val registerStatus = MutableLiveData<RegisterStatus>()
    fun registerUser(login : String, pass : String) {
        val userRepository = UserRepository(userDao = userDao)

        val regStatus = RegisterStatus()
        try {
            val status = userRepository.registerUser(login, pass)
            regStatus.status = status
        }catch (e: UserNotFoundException){
            regStatus.registerError = "User not found"
        }

        registerStatus.value = regStatus
    }

    fun getRegisterStatus(): MutableLiveData<RegisterStatus>{
        return registerStatus
    }

}