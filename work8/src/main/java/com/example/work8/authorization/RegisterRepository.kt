package com.example.work8.authorization

import androidx.lifecycle.MutableLiveData
import com.example.work8.model.user.UserAlreadyExistsException
import com.example.work8.model.user.UserDao
import com.example.work8.model.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class RegisterStatus(
    var status : Boolean = false,
    var registerError : String = ""
)
class RegisterRepository@Inject constructor(
    private val userDao: UserDao
) {
    private val registerStatus = MutableLiveData<RegisterStatus>()
    suspend fun registerUser(login : String, pass : String) {

        val userRepository = UserRepository(userDao = userDao)


        withContext(Dispatchers.IO) {
            var regStatus = RegisterStatus()

            if(registerStatus.value != null){
                regStatus = registerStatus.value!!
            }

            try {
                val status = userRepository.registerUser(login, pass)
                regStatus.status = status
            } catch (e: UserAlreadyExistsException) {
                regStatus.status = false
                regStatus.registerError = e.message.toString()
            }
            registerStatus.postValue(regStatus)

        }
    }

    fun getRegisterStatus(): MutableLiveData<RegisterStatus>{
        return registerStatus
    }

}