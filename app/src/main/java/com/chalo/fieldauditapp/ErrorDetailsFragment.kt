package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionFineBinding
import com.chalo.fieldauditapp.databinding.FragmentErrorDetailsBinding

class ErrorDetailsFragment : Fragment() {

    private var _binding: FragmentErrorDetailsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentErrorDetailsBinding.inflate(inflater,container, false)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity?)?.setDrawerEnabled(false)
        binding.errorDetailsOK.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity?)?.setDrawerEnabled(false)
    }

}