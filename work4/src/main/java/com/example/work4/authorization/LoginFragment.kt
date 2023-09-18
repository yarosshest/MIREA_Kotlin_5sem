package com.example.work4.authorization

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.work4.R
import com.example.work4.databinding.FragmentLoginBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
private val repository: LoginRepository
) : ViewModel() {
    val loginStatus: LiveData<LoginStatus> = repository.getLoginStatus()

    fun register(login: String, pass: String) {
        viewModelScope.launch {
            repository.loginUser(login, pass)
        }
    }
}
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.buttonLogin.setOnClickListener {
            val login = binding.edittTextTextLogin.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

            viewModel.register(login, pass)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner) {
            updateFragment(it)
        }
    }

    private fun updateFragment(status: LoginStatus){
        if (status.status){
            findNavController().navigate(R.id.loginFragment)
        }
        if( status.loginError != ""){
            binding.textError.text = status.loginError
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}