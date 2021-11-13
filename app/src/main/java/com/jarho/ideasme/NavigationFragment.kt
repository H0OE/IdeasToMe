package com.jarho.ideasme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jarho.ideasme.databinding.FragmentNavigationBinding
import androidx.fragment.app.FragmentActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity


class NavigationFragment:Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        bottomNavigationView = binding.bottomNavigationView
//        val navHostFragment = fragmentManager?.findFragmentById(
//            R.id.nav_host_fragment_container) as NavHostFragment
//        val navController = navHostFragment.navController
//        bottomNavigationView.setupWithNavController(navController)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }




}