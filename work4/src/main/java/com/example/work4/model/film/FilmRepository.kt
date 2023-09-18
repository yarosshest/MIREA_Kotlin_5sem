package com.example.work4.model.film

import com.example.work4.api.Api
import com.example.work4.api.RetrofitHelper

class ProductsNotFoundException(message: String = "Products not found") : Exception(message)

class FilmRepository(private val filmDao: FilmDao)  {
    private val api = RetrofitHelper.getInstance().create(Api::class.java)

    fun findFilms(line : String): List<Film>? {
        val response = api.find(
            line = line,
        ).execute()

        if (response.isSuccessful){
            val films = response.body()
            if (films != null) {
                for (f in films)
                    filmDao.insertFilm(f)
                return films
            }
            throw ProductsNotFoundException()
        } else {
            if (response.code() == 404){
                throw ProductsNotFoundException()
            }
        }
        return null
    }


}