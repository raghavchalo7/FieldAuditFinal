package com.chalo.fieldauditapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        (activity as MainActivity?)?.setDrawerEnabled(false)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.redirectBusDetailsDoneToBusSelection.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
            val token= sharedPreferences?.getString("token",null)
            val actionDest=if (token==null) R.id.action_busDetailsDoneFragment_to_loginFragment2 else R.id.action_busDetailsDoneFragment_to_busSelectionFragment2
            findNavController().navigate(actionDest)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as MainActivity?)?.setDrawerEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity?)?.setDrawerEnabled(true)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}