package com.example.work4.api

import com.example.work4.model.film.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("register")
    fun register(
        @Query("log") login: String,
        @Query("password") password: String,
    ): Call<Map<String,Int>>

    @GET("login")
    fun login(
        @Query("log") login: String,
        @Query("password") password: String,
    ): Call<Map<String,Int>>

    @GET("find")
    fun find(
        @Query("line") line: String
    ): Call<List<Film>>

    @GET("get_film")
    fun getFilm(
        @Query("prod_id") id: Int
    ): Call<Film>
}