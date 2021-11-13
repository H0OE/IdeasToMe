package com.jarho.ideasme.profile.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jarho.ideasme.databinding.FragmentFavoritesBinding
import com.jarho.ideasme.databinding.FragmentProfileBinding

class FavoritesFragment  : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)



        //(ContextCompat.getDrawablre(getActivity(),R.drawable.ic_search_icon));

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }




}