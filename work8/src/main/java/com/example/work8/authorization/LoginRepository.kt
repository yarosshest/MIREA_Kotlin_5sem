package com.example.work8.authorization

import androidx.lifecycle.MutableLiveData
import com.example.work8.model.user.UserDao
import com.example.work8.model.user.UserNotFoundException
import com.example.work8.model.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


data class LoginStatus(
    var status : Boolean = false,
    var loginError : String = ""
)

class LoginRepository @Inject constructor(
private val userDao: UserDao
) {
    private val loginStatus = MutableLiveData<LoginStatus>()
    suspend fun loginUser(login: String, pass: String) {

        val userRepository = UserRepository(userDao = userDao)


        withContext(Dispatchers.IO) {
            var logStatus = LoginStatus()

            if (loginStatus.value != null) {
                logStatus = loginStatus.value!!
            }

            try {
                val status = userRepository.loginUser(login, pass)
                logStatus.status = status
            } catch (e: UserNotFoundException) {
                logStatus.status = false
                logStatus.loginError = e.message.toString()
            }
            loginStatus.postValue(logStatus)

        }
    }

    fun getLoginStatus(): MutableLiveData<LoginStatus> {
        return loginStatus
    }
}