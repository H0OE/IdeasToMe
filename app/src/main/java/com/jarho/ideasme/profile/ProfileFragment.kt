package com.jarho.ideasme.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.FragmentProfileBinding
import com.jarho.ideasme.model.FeedModel
import com.jarho.ideasme.prefs.UserApplication
import com.jarho.ideasme.prefs.UserApplication.Companion.prefs
import com.jarho.ideasme.viewModel.FeedAdapter
import com.jarho.ideasme.viewModel.PostsViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)



        //(ContextCompat.getDrawablre(getActivity(),R.drawable.ic_search_icon));

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showUser()
        logOut()
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_posts_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationProfile.setupWithNavController(navController)

    }


    fun showUser(){

        view?.let {
            Glide.with(it.context).load(FirebaseAuth.getInstance().currentUser?.photoUrl)
                .transform( CenterCrop(), CircleCrop())
                .into(binding.ivUser)
        }


        binding.tvUserName.text = FirebaseAuth.getInstance().currentUser?.displayName
    }



    fun logOut(){
        binding.ivContact.setOnClickListener {
            val bundle = bundleOf(

                "email" to prefs.getEmail(),
                "user" to binding.tvUserName.text)

            findNavController().navigate(R.id.action_profileFragment_to_popUp_info,bundle)
        }
        binding.viewContact.setOnClickListener {
            val bundle = bundleOf(
                "email" to FirebaseAuth.getInstance().currentUser?.email.toString(),
                "user" to FirebaseAuth.getInstance().currentUser?.displayName.toString())

            findNavController().navigate(R.id.action_homeFragment2_to_popUp_info,bundle)

        }
    }
}