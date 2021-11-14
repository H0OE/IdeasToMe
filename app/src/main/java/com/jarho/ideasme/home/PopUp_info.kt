package com.jarho.ideasme.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jarho.ideasme.databinding.PopupInfoBinding

class PopUp_info : BottomSheetDialogFragment() {
    private var _binding: PopupInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PopupInfoBinding.inflate(inflater, container, false)



        binding.tvContent.text =  arguments?.getString("email")
        return binding.root


    }
}