package com.example.work8.find

import androidx.lifecycle.MutableLiveData
import com.example.work8.model.film.Film
import com.example.work8.model.film.FilmDao
import com.example.work8.model.film.FilmRepository
import com.example.work8.model.film.ProductsNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class FindStatus(
    var status : Boolean = false,
    var findError : String = ""
)

class FindRepository@Inject constructor(
    private val filmDao: FilmDao
) {
    private val listFilms = MutableLiveData<List<Film>>()
    private val findStatus = MutableLiveData<FindStatus>()

    suspend fun findFilms(line: String) {
        val filmRepository = FilmRepository(filmDao = filmDao)


        withContext(Dispatchers.IO) {
            val status = FindStatus()

            try {
                val films = filmRepository.findFilms(line = line)!!

                listFilms.postValue(films)
                status.status = true
                status.findError = ""
            } catch (e: ProductsNotFoundException) {
                status.status = false
                status.findError = e.message.toString()
            }
            findStatus.postValue(status)
        }

    }

    fun getListFilms(): MutableLiveData<List<Film>> {
        return listFilms
    }

    fun getFindStatus():MutableLiveData<FindStatus>{
        return findStatus
    }
}