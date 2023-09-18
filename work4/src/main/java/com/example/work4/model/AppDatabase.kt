package com.example.work4.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.work4.model.film.Film
import com.example.work4.model.film.FilmDao
import com.example.work4.model.user.User
import com.example.work4.model.user.UserDao


@Database(entities = [User::class, Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getFilmDao(): FilmDao
}