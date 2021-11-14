package com.jarho.ideasme.add

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jarho.ideasme.R
import com.jarho.ideasme.R.color.text
import com.jarho.ideasme.databinding.FragmentAddBinding
import com.jarho.ideasme.prefs.UserApplication.Companion.prefs

class AddFragment: Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnPost.setOnClickListener {
            isText()

        }
        binding.etFilter.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                //Perform Code

                    addChip()
                binding.etFilter.text = null
                return@OnKeyListener true
            }
            false
        })
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = binding.chipGroup.findViewById<Chip>(checkedId)
            binding.chipGroup.removeView(chip)
        }


    }

    private fun isText() {
        if (binding.editText.text.isEmpty()) {
            binding.editText.error = "write your idea"
            Toast.makeText(requireContext(), "Your idea is empty", Toast.LENGTH_SHORT).show();

        } else {
            toFirestore()
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun addChip() {
        if(binding.chipGroup.size<3){
        if (binding.etFilter.text.isNotEmpty()) {
            val chip = Chip(requireContext())
            val chipDrawable = ChipDrawable.createFromAttributes(
                requireContext(),
                null,
                0,
                R.style.CustomChip
            )

            chip.setChipDrawable(chipDrawable);

            chip.text = binding.etFilter.text
            chip.setTextColor(Color.WHITE)
            chip.textSize = 24f


            chip.id = ViewCompat.generateViewId()



            binding.chipGroup.addView(chip)
        }}else{
            Toast.makeText(requireContext(), "Max 3 tags", Toast.LENGTH_SHORT).show();

        }


    }

    private fun getChipGroup():ArrayList<String> {
        val chipsName: ArrayList<String> = ArrayList()

        for (i in 0 until binding.chipGroup.getChildCount()) {
            val email = (binding.chipGroup.getChildAt(i) as Chip).text.toString()
            chipsName.add(email)

        }
        return chipsName

    }

    private fun toFirestore(){
        val data = hashMapOf(

            "urlImage" to FirebaseAuth.getInstance().currentUser?.photoUrl.toString(),
            "chipNamea" to getChipGroup(),
            "description" to binding.editText.text.toString(),
            "userNick" to prefs.getUser(),
            "userMail" to prefs.getEmail(),
            "created" to FieldValue.serverTimestamp(),

        )
        db.collection("posts").document(prefs.getEmail() + Math.random().toString().substring(2, 4)).set(data)
        Toast.makeText(activity, "Agregado", Toast.LENGTH_SHORT).show()
    }
}