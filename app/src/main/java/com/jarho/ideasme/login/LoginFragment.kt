package com.jarho.ideasme.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.FragmentLoginBinding

class LoginFragment:Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogIn.setOnClickListener{
            navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_loginFragment3_to_navigationFragment)
        }
    }
}