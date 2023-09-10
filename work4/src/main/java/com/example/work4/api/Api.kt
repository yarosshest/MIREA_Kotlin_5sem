package com.example.work4.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Dictionary

data class Film (
    val id : Int,
    val photo : String,
    val name: String,
    val description: String
)


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