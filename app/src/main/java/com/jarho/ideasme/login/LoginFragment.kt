package com.jarho.ideasme.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.FragmentLoginBinding
import com.jarho.ideasme.prefs.UserApplication.Companion.prefs

class LoginFragment:Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (prefs.getEmail().isNotEmpty()) {
            goFeed()
        }

        binding.btnLogIn.setOnClickListener {
            signIn()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {


                                prefs.saveEmail(it.result.user?.email.toString())
                                it.result.user?.displayName?.let { it1 -> prefs.saveUser(it1) }
                                Log.d("debug", prefs.getUser())





                                goFeed()
                            } else {
                                error("Error al hacer Sign In")//todo Strings
                            }
                        }


                } else {
                    error("Error al hacer Sign In")
                }


            } catch (e: ApiException) {
                error("No se pudo conectar")
            }
        }
    }

    private fun goFeed() {
        findNavController().navigate(R.id.action_loginFragment3_to_navigationFragment)



    }

    private fun error(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun signIn() {

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        val signInIntent = googleSignInClient.signInIntent
        googleSignInClient.signOut()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

}