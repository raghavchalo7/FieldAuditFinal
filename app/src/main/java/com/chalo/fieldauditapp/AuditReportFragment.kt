package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalo.fieldauditapp.databinding.FragmentAuditReportBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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

        itemAdapter.onItemClick={
            Toast.makeText(context, "Pressed", Toast.LENGTH_LONG).show()
            val  bottomSheetDialog: BottomSheetDialog =  BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.auditdetails)
//            val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.redirectBusSelectFineToBusDetailsDone)
//            if (b2 != null) {
//                b2.setOnClickListener {
//                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
            val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.dimissAudit)
            if (b2 != null) {
                b2.setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            }
//                }
//            }
            bottomSheetDialog.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}