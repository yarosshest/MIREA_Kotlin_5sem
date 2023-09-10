package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.work2.databinding.FragmentFirstFilmBinding


class FirstFilmFragmentRepository {
    private val data = MutableLiveData<Int>()
    fun getId(): LiveData<Int> {
        data.value = R.drawable.fores
        return data
    }
}

class FirstFilmFragmentViewModel : ViewModel() {
    private val dataRepository = FirstFilmFragmentRepository()
    val id: LiveData<Int> = dataRepository.getId()
}

class FirstFilmFragment : Fragment() {

    private lateinit var viewModel: FirstFilmFragmentViewModel

    private var _binding: FragmentFirstFilmBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FirstFilmFragmentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.id.value?.let { binding.imageView2.setImageResource(it) }

    }

}