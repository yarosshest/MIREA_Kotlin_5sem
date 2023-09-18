package com.example.work4.find

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
import androidx.recyclerview.widget.RecyclerView
import com.example.work4.R
import com.example.work4.databinding.FragmentFindBinding
import com.example.work4.model.film.Film
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

    fun createAdapter(films : List<Film>) : FilmAdapter{
        return FilmAdapter(films)
    }
}
@AndroidEntryPoint
class FindFragment : Fragment(R.layout.fragment_find) {
    private val viewModel: FindViewModel by viewModels()

    private var _binding: FragmentFindBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFindBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchName.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.findFilms(p0)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        viewModel.listFilms.observe(viewLifecycleOwner){
            val adap = viewModel.createAdapter(viewModel.listFilms.value!!)
            binding.filmRecyclerView.adapter = adap
        }

    }

}