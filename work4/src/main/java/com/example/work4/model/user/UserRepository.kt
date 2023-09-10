package com.example.work4.model.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository(private val userDao: UserDao) {
    private val allUsers = MutableLiveData<List<User>>()

    fun getAllUsers(): LiveData<List<User>> {
        allUsers.value = userDao.getAllUsers()
        return allUsers
    }

}