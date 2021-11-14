package com.jarho.ideasme.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    }
}