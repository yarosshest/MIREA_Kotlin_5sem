package com.example.work4.authorization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.work4.R
import com.example.work4.databinding.FragmentRegisterBinding
import com.example.work4.model.user.UserDao
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel()  {
    val registerStatus: LiveData<RegisterStatus> = repository.getRegisterStatus()

    fun register(login : String, pass : String){
        viewModelScope.launch {
            repository.registerUser(login, pass)
        }
    }
}
@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    @Inject
    lateinit var userDao: UserDao

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.registerStatus.observe(viewLifecycleOwner) {
                updateFragment(it)
        }


        binding.buttonRegister.setOnClickListener {
            val login = binding.edittTextTextLogin.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

            viewModel.register(login, pass)
        }

    }

    private fun updateFragment(status: RegisterStatus){


        Log.d("observe",  status.registerError)
        Log.d("status",  status.status.toString())
        if (status.status){
            findNavController().navigate(R.id.loginFragment)
        }
        if( status.registerError != ""){
            binding.textError.text = status.registerError
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}