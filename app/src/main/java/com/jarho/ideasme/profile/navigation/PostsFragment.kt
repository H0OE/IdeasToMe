package com.jarho.ideasme.profile.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.FragmentMinePostsBinding
import com.jarho.ideasme.databinding.FragmentProfileBinding
import com.jarho.ideasme.model.FeedModel
import com.jarho.ideasme.viewModel.FeedAdapter
import com.jarho.ideasme.viewModel.PostsViewModel

class PostsFragment  : Fragment() {
    private var _binding: FragmentMinePostsBinding? = null
    private val binding get() = _binding!!


    private val feedAdapter = FeedAdapter()
    private val postsModel: PostsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMinePostsBinding.inflate(inflater, container, false)



        //(ContextCompat.getDrawablre(getActivity(),R.drawable.ic_search_icon));

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMinePosts)

        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        LinearSnapHelper().attachToRecyclerView(recyclerView)

        postsModel.postList.observe(viewLifecycleOwner, {
            feedAdapter.addAll(it as MutableList<FeedModel>)
        }
        )
        postsModel.updateMinePost()

        feedAdapter.setOnFeedItemClickListener {

            val userNick = it.userNick


            val description: String = it.description
            val userEmail : String = it.userMail
            val urlImage = it.urlImage



            val bundle = bundleOf(
                "description" to description,
                "email" to userEmail,
                "user" to userNick)

            findNavController().navigate(R.id.action_postsFragment_to_popUp_info2,bundle)

        }

    }




}