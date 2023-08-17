package com.chalo.fieldauditapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chalo.fieldauditapp.databinding.ConfirmdetailsBinding
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionFineBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class BusSelectionFineFragment : Fragment() {

    private var _binding: FragmentBusSelectionFineBinding?=null
    private val binding get() = _binding!!
    val args:BusSelectionFineFragmentArgs by navArgs()

    var fineColl:Int=0
    var fineCount:Int=0

//    private var _binding2: ConfirmdetailsBinding?=null
//    private val binding2 get() = _binding2!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBusSelectionFineBinding.inflate(inflater,container, false)

        val amount=args.dataVRec

        val busNo=findVal(amount,"busNo")
        val currentStopName=findVal(amount,"currentStopName")

        val deboardedPassengerCount=findVal(amount,"deboardedPassengerCount")
        binding.deboardTV.text=deboardedPassengerCount

        val currentPassengerCount=findVal(amount,"currentPassengerCount")
        binding.passengerTV.text=currentPassengerCount


        if(fineCount!=0)
        {
            binding.fineTV.visibility=View.VISIBLE
        }
        else
        {
            binding.fineTV.visibility=View.INVISIBLE
        }

        binding.issueFine.setOnClickListener {

            fineCount=fineCount+1
            val fine1:EditText?=binding.fineAmountET
            var fine:Int=0
            if (fine1 != null) {
                fine=fine1.text.toString().toInt()
            }

            fineColl=fineColl+fine
            binding.fineCountTV.text=fineCount.toString()
            binding.fineCollectedTV.text=fineColl.toString()
            binding.fineTV.visibility=View.VISIBLE

        }

        binding.endAudit.setOnClickListener {

            val  bottomSheetDialog: BottomSheetDialog =  BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.confirmdetails)

            val busTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.busTV2)
            if (busTv2 != null) {
                busTv2.text=busNo
            }

            val routeTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.routeTV2)
            if (routeTv2 != null) {
                routeTv2.text=busNo
            }

            val stopTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.stopTV2)
            if (stopTv2 != null) {
                stopTv2.text=currentStopName
            }

            val passengerTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.passengerTV2)
            if (passengerTv2 != null) {
                passengerTv2.text=currentPassengerCount
            }

            val finecountTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.fineCountTV2)
            if (finecountTv2 != null) {
                finecountTv2.text=fineCount.toString()
            }

            val fineTv2: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.fineTV2)
            if (fineTv2 != null) {
                fineTv2.text=fineColl.toString()
            }


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


    fun findVal(amount:String,key:String):String{
        val index:Int = amount.indexOf(key)
        val additional=index+key.length+2
        val indexMid=amount.indexOf("\"", startIndex = additional)
        var index2 = amount.indexOf("\"", startIndex = indexMid+2)
        val answer=amount.subSequence(indexMid+1,index2).toString()
        //Toast.makeText(context,"Got="+answer,Toast.LENGTH_LONG).show()
        return answer
    }

}