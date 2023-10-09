package com.example.work8.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    val loginStatus: LiveData<LoginStatus> = repository.getLoginStatus()

    fun login(login: String, pass: String) {
        viewModelScope.launch {
            repository.loginUser(login, pass)
        }
    }
}