package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.databinding.FragmentBusDetailsDoneBinding
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding

class BusDetailsDoneFragment : Fragment() {

    private var _binding: FragmentBusDetailsDoneBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusDetailsDoneBinding.inflate(inflater, container, false)

        binding.redirectBusDetailsDoneToBusSelection.setOnClickListener {
            findNavController().navigate(R.id.action_busDetailsDoneFragment_to_busSelectionFragment2)
        }
        return binding.root
    }

}