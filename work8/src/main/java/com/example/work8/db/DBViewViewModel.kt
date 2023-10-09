package com.example.work8.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.work8.model.film.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DBViewViewModel @Inject constructor(
    private val repository: DBViewRepository
) : ViewModel() {
    var listFilms: LiveData<List<Film>> = repository.getListFilms()

    fun getAllFilms(){
        viewModelScope.launch {
            repository.findFilms()
        }
    }
}