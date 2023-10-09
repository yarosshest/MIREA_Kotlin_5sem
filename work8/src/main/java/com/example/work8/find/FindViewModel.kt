package com.example.work8.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.widget.SearchView
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.work8.model.film.Film
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val repository: FindRepository
) : ViewModel()  {
    var recyclerView: LiveData<RecyclerView> = MutableLiveData<RecyclerView>()
    var findStatus: LiveData<FindStatus> = repository.getFindStatus()
    var listFilms: LiveData<List<Film>> = repository.getListFilms()

    fun findFilms(line: String){
        viewModelScope.launch {
            repository.findFilms(line)
        }
    }
}