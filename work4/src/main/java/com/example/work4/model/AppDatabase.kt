package com.example.work4.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.work4.model.user.User
import com.example.work4.model.user.UserDao
import javax.inject.Singleton


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}