package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.chalo.fieldauditapp.databinding.FragmentErrorDetailsBinding
import com.chalo.fieldauditapp.databinding.FragmentNoNetworkBinding

class NoNetworkFragment : Fragment() {

    private var _binding: FragmentNoNetworkBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentNoNetworkBinding.inflate(inflater,container, false)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity?)?.setDrawerEnabled(false)


        binding.noNetworkOK.setOnClickListener {
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