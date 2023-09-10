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
import com.example.work2.databinding.FragmentTextBinding


class TextFragmentDataRepository {
    private val data = MutableLiveData<String>()
    fun getText(): LiveData<String> {
        data.value = "Данное приложение будет предназначено для поиска и рекомендации фильмов на основе вкуса пользователя"
        return data
    }
}

class TextFragmentViewModel : ViewModel() {
    private val dataRepository = TextFragmentDataRepository()
    val text: LiveData<String> = dataRepository.getText()
}


class TextFragment : Fragment() {

    private lateinit var viewModel: TextFragmentViewModel

    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TextFragmentViewModel::class.java]
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = viewModel.text.value

        binding.exit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this).commit()
        }
    }
}