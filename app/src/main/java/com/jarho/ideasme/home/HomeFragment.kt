package com.jarho.ideasme.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.FragmentHomeBinding
import com.jarho.ideasme.model.FeedModel
import com.jarho.ideasme.viewModel.FeedAdapter
import com.jarho.ideasme.viewModel.PostsViewModel

class HomeFragment:Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val feedAdapter = FeedAdapter()
    private val postsModel: PostsViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFeed)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        LinearSnapHelper().attachToRecyclerView(recyclerView)

        postsModel.feedList.observe(viewLifecycleOwner, {
            feedAdapter.addAll(it as MutableList<FeedModel>)
        }
        )
        postsModel.updatePost()
        searching()


        feedAdapter.setOnFeedItemClickListener {

            val userNick = it.userNick


            val description: String = it.description
            val userEmail: String = it.userMail
            val urlImage = it.urlImage


            val bundle = bundleOf(
                "description" to description,
                "email" to userEmail,
                "user" to userNick
            )

            findNavController().navigate(R.id.action_homeFragment2_to_popUp_info, bundle)

        }
    }


    private fun searching(){

//        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvFeed)
//        if (recyclerView != null) {
//            recyclerView.adapter = feedAdapter
//        }
//        if (recyclerView != null) {
//            recyclerView.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        }
//
//        LinearSnapHelper().attachToRecyclerView(recyclerView)
        binding.textsearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                postsModel.searchList.observe(viewLifecycleOwner, {
                    feedAdapter.addAll(it as MutableList<FeedModel>)


                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    postsModel.updateSearch(newText)
                }

                return false
            }


        })

    }
}




