package com.example.work8.model.film

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmDao {
    @Insert
    fun insert(film: Film)
    @Query("SELECT * FROM films")
    fun getAllFilms(): List<Film>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun lightInsert(film: Film)

    @Insert
    fun insertFilm(film: Film)
    @Update
    fun updateFilm(film: Film)
    @Delete
    fun deleteFilm(film: Film)
}