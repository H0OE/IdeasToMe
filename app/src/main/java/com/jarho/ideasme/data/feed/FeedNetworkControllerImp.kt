package com.jarho.ideasme.data.feed

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jarho.ideasme.model.FeedModel
import com.jarho.ideasme.prefs.UserApplication.Companion.prefs
import kotlinx.coroutines.tasks.await

class FeedNetworkControllerImp : FeedNetworkController {

    val db = Firebase.firestore


    override suspend fun getAllPosts(): List<FeedModel> {
        val response = db.collection("posts").orderBy(
            "created", Query.Direction.DESCENDING).get().await()
        return response.toObjects(FeedModel::class.java)
    }

    override suspend fun getAllFavoritesPosts(): List<FeedModel> {
        val tempt = db.collection("posts")
            .whereEqualTo("userMail", prefs.getEmail())
            .orderBy("created", Query.Direction.DESCENDING)


        val response =tempt.get().await()
        return response.toObjects(FeedModel::class.java)    }

    override suspend fun getAllMyPosts(): List<FeedModel> {
        val tempt = db.collection("posts")
            .whereEqualTo("userMail", prefs.getEmail())
            .orderBy("created", Query.Direction.DESCENDING)


        val response =tempt.get().await()
        return response.toObjects(FeedModel::class.java)    }

    override suspend fun getAllSearch(search:String): List<FeedModel> {
        val response = db.collection("posts").orderBy(
            "userNick").startAt(search).endAt(search + "\uf8ff").get().await()

        return response.toObjects(FeedModel::class.java)   }



}