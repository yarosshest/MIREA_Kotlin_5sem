package com.example.work4.authorization

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.work4.R
import com.example.work4.databinding.FragmentRegisterBinding
import com.example.work4.model.user.UserDao
import com.example.work4.modules.DaggerAppComponent
import javax.inject.Inject


class RegisterViewModel : ViewModel() {
    private val repository  = RegisterRepository()
    val registerStatus: LiveData<RegisterStatus> = repository.getRegisterStatus()

    fun register(login : String, pass : String, userDao: UserDao){
        repository.registerUser(login, pass, userDao)
    }
}

class RegisterFragment : Fragment() {

    @Inject
    lateinit var userDao: UserDao


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

//        val component = DaggerAppComponent.builder().build()
//        component.inject(this)

        viewModel.registerStatus.observe(viewLifecycleOwner) {
            if (it.status){
                findNavController().navigate(R.id.loginFragment)
            }
            if( it.registerError != ""){
                binding.textError.text = it.registerError
            }
        }

        binding.buttonRegister.setOnClickListener {
            val login = binding.edittTextTextLogin.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

            viewModel.register(login, pass, userDao)
        }

    }

}