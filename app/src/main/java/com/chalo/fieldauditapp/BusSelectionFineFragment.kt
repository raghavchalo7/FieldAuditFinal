package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.databinding.ConfirmdetailsBinding
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionFineBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class BusSelectionFineFragment : Fragment() {

    private var _binding: FragmentBusSelectionFineBinding?=null
    private val binding get() = _binding!!

    private var _binding2: ConfirmdetailsBinding?=null
    private val binding2 get() = _binding2!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBusSelectionFineBinding.inflate(inflater,container, false)

        binding.issueFine.setOnClickListener {
            val  bottomSheetDialog: BottomSheetDialog =  BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.confirmdetails)
//            val view:View=View.inflate(confirmdetails)
//            val dialogue=BottomSheetDialog(this)
//            dialogue.setContentView(view)

            //val view = layoutInflater.inflate(R.layout.confirmdetails, null)
            val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.redirectBusSelectFineToBusDetailsDone)
            if (b2 != null) {
                b2.setOnClickListener {
                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
                    bottomSheetDialog.dismiss()
                }
            }

            val btnClose = view?.findViewById<Button>(R.id.redirectBusSelectFineToBusDetailsDone)

            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            if (btnClose != null) {
                btnClose.setOnClickListener {
                    // on below line we are calling a dismiss
                    // method to close our dialog.
                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
                }
            }

            bottomSheetDialog.show()
        }


//        binding2.redirectBusSelectFineToBusDetailsDone.setOnClickListener {
//            findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
//        }

        return binding.root
    }

}