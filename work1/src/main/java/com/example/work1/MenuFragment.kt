package com.example.work1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.work1.databinding.FragmentMenuBinding
import com.example.work1.databinding.FragmentTextBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialCardView1.setOnClickListener {
            findNavController().navigate(R.id.firstFilmFragment)
        }

        binding.materialCardView2.setOnClickListener {
            findNavController().navigate(R.id.secondFilmFragment)
        }
    }
}