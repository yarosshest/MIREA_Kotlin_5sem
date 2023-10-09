package com.example.work8.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel()  {
    val registerStatus: LiveData<RegisterStatus> = repository.getRegisterStatus()

    fun register(login : String, pass : String){
        viewModelScope.launch {
            repository.registerUser(login, pass)
        }
    }
}