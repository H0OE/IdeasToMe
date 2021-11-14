package com.jarho.ideasme.data.feed

import com.jarho.ideasme.model.FeedModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FeedRepository (val network : FeedNetworkController){

    fun getAllPost() : Flow<List<FeedModel>> {
        return flow {
            emit(network.getAllPosts())
        }
    }

    fun getAllMyPosts() : Flow<List<FeedModel>> {
        return flow {
            emit(network.getAllMyPosts())
        }
    }
    fun getAllSearch(text:String) : Flow<List<FeedModel>> {
        return flow {
            emit(network.getAllSearch(text))
        }
    }


}