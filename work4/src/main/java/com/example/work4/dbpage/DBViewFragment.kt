package com.example.work4.dbpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.work4.R
import com.example.work4.authorization.LoginRepository
import com.example.work4.authorization.LoginStatus
import com.example.work4.authorization.LoginViewModel
import com.example.work4.databinding.FragmentDBViewBinding
import com.example.work4.databinding.FragmentLoginBinding
import com.example.work4.databinding.FragmentRegisterBinding
import com.example.work4.model.film.Film
import dagger.hilt.android.AndroidEntryPoint
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

    fun createAdapter(films: List<Film>):DBViewAdapter{
        return DBViewAdapter(films)
    }

}
@AndroidEntryPoint
class DBViewFragment : Fragment(R.layout.fragment_d_b_view) {
    private val viewModel: DBViewViewModel by viewModels()

    private var _binding: FragmentDBViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmRecyclerView.layoutManager = GridLayoutManager(context, 1)
        viewModel.listFilms.observe(viewLifecycleOwner){
            binding.filmRecyclerView.adapter = viewModel.createAdapter(it)
        }
        viewModel.getAllFilms()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}