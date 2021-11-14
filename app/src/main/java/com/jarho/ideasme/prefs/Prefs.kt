package com.jarho.ideasme.prefs

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseUser

class Prefs(val context: Context) {

    private val SHARED_NAME = "auth"
    private val SHARED_EMAIL = "email"
    private val SHARED_PHOTO = "photo"
    private val SHARED_USER = "user"



    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveEmail(email: String) {
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getEmail(): String {
        return storage.getString(SHARED_EMAIL, "")!!
    }
    fun savePhoto(photo: Uri?) {
        storage.edit().putString(SHARED_PHOTO, photo?.toString()).apply()
    }

    fun getPhoto(): String {
        return storage.getString(SHARED_PHOTO, "")!!
    }
    fun saveUser(user: String) {
        storage.edit().putString(SHARED_USER, user).apply()
    }

    fun getUser(): String {
        return storage.getString(SHARED_USER, "")!!
    }


    fun erase() {
        storage.edit().clear().apply()
    }
}