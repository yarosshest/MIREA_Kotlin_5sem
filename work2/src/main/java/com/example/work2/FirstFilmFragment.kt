package com.example.work2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work2.databinding.FragmentFirstFilmBinding

class FirstFilmFragment : Fragment() {

    private var _binding: FragmentFirstFilmBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

}