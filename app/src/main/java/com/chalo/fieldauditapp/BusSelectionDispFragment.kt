package com.chalo.fieldauditapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionDispBinding


class BusSelectionDispFragment : Fragment() {

    private var _binding: FragmentBusSelectionDispBinding?=null
    private val binding get() = _binding!!

    val args: BusSelectionDispFragmentArgs by navArgs()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBusSelectionDispBinding.inflate(inflater,container, false)

        //(activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as MainActivity?)?.setDrawerEnabled(false)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //(activity as MainActivity?)?.getActionBar()?.setDisplayHomeAsUpEnabled(false)

        val amount = args.dataVRec
//        val busNo=findVal(amount,"busNo")
//        binding.busNoTV.text=busNo

        val currentStopName=findVal(amount,"currentStopName")
        binding.currentStopNameTV.text=currentStopName

        val currentPassengerCount=findVal(amount,"currentPassengerCount")
        binding.currentPassengerCountTV.text=currentPassengerCount

        val busNo=findVal(amount,"busNo")
        binding.currentBusNoTV.text=busNo

        binding.redirectBusDispToBusSelectFine.setOnClickListener {
            val action=BusSelectionDispFragmentDirections.actionBusSelectionDispFragmentToBusSelectionFineFragment(amount)
            findNavController().navigate(action)
        }
        val callback=object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Toast.makeText(context,"CHECK",Toast.LENGTH_SHORT).show()
                return
            }
        }
        MainActivity().getOnBackPressedDispatcher().addCallback(callback)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val amount = args.dataVRec
        val busNo=findVal(amount,"busNo")
        //(activity as AppCompatActivity?)!!.supportActionBar!!.title=busNo
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(true)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as MainActivity?)?.setDrawerEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        (activity as MainActivity?)?.setDrawerEnabled(true)
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    //onBackPressed

//    @Override
//    fun onBackPressed() {
//
//        // To execute back press
//        // super.onBackPressed()
//
//        // To do something else
//        Toast.makeText(context, "Back Button Pressed", Toast.LENGTH_SHORT).show()
//    }

//    @Override
//    fun onBackPressed() {
//        return
//    }
//    @Override
//    fun onKeyDown(): Boolean {
//        return false
//    }
//
//
//    @Override
//    fun onBackPressed() {
//        Toast.makeText(context,"*********HI*****",Toast.LENGTH_SHORT).show()
//    }

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