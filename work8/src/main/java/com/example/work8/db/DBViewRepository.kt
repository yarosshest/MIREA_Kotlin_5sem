package com.example.work8.db

import androidx.lifecycle.MutableLiveData
import com.example.work8.model.film.Film
import com.example.work8.model.film.FilmDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DBViewRepository@Inject constructor(
    private val filmDao: FilmDao
) {
    private val listFilms = MutableLiveData<List<Film>>()

    suspend fun findFilms() {
        withContext(Dispatchers.IO){
            listFilms.postValue(filmDao.getAllFilms())
        }
    }

    fun getListFilms():MutableLiveData<List<Film>>{
        return listFilms
    }
}