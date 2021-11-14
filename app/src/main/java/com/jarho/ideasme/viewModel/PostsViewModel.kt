package com.jarho.ideasme.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jarho.ideasme.data.feed.FeedNetworkControllerImp
import com.jarho.ideasme.data.feed.FeedRepository
import com.jarho.ideasme.model.FeedModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PostsViewModel : ViewModel() {
    val feedRepository = FeedRepository(FeedNetworkControllerImp())
    val feedList = MutableLiveData<List<FeedModel>>()
    val postList = MutableLiveData<List<FeedModel>>()
    val searchList = MutableLiveData<List<FeedModel>>()


    fun updatePost() {
        feedRepository.getAllPost().onEach {
            feedList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updateMinePost() {
        feedRepository.getAllMyPosts().onEach {
            postList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updateSearch(text:String) {
        feedRepository.getAllSearch(text).onEach {
            searchList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}