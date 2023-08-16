package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalo.fieldauditapp.databinding.FragmentAuditReportBinding

class AuditReportFragment : Fragment() {

    private var _binding: FragmentAuditReportBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAuditReportBinding.inflate(inflater,container, false)
        //Recyclerview
        val routelist= RouteConstant.getRouteData()
        val itemAdapter=CustomAdapter(routelist)
        binding.recyclerview.layoutManager= LinearLayoutManager(context)
        binding.recyclerview.adapter=itemAdapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}