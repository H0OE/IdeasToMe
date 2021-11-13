package com.jarho.ideasme.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.jarho.ideasme.databinding.FragmentAddBinding

class AddFragment: Fragment() {
private var _binding: FragmentAddBinding? = null
private val binding get() = _binding!!
private lateinit var navController: NavController
private lateinit var bottomNavigationView: BottomNavigationView


override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    _binding = FragmentAddBinding.inflate(inflater, container, false)



    //(ContextCompat.getDrawablre(getActivity(),R.drawable.ic_search_icon));

    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
        val chip = binding.chipGroup.findViewById<Chip>(checkedId)
        binding.chipGroup.removeView(chip)
    }
}




}