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
import androidx.navigation.fragment.findNavController
import com.example.work2.databinding.FragmentMenuBinding


class MenuFragmentRepository {
    private val dataF = MutableLiveData<Int>()
    private val dataS = MutableLiveData<Int>()

    fun getIdF(): LiveData<Int> {
        dataF.value = R.drawable.getsbi
        return dataF
    }
    fun getIdS(): LiveData<Int> {
        dataS.value = R.drawable.getsbi
        return dataS
    }
}

class MenuFragmentViewModel : ViewModel() {
    private val dataRepository = MenuFragmentRepository()
    val idF: LiveData<Int> = dataRepository.getIdF()
    val idS: LiveData<Int> = dataRepository.getIdS()

}

class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuFragmentViewModel

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MenuFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.idF.value?.let { binding.imageView1.setImageResource(it) }
        viewModel.idS.value?.let { binding.imageView2.setImageResource(it) }

        binding.materialCardView1.setOnClickListener {
            findNavController().navigate(R.id.firstFilmFragment)
        }

        binding.materialCardView2.setOnClickListener {
            findNavController().navigate(R.id.secondFilmFragment)
        }
    }
}