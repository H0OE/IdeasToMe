package com.jarho.ideasme.data.feed

import com.jarho.ideasme.model.FeedModel


interface FeedNetworkController {
    suspend fun getAllPosts(): List<FeedModel>

    suspend fun getAllFavoritesPosts(): List<FeedModel>

    suspend fun getAllMyPosts(): List<FeedModel>
}