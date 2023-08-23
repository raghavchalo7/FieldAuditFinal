package com.chalo.fieldauditapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionDispBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding

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

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val amount = args.dataVRec
//        val busNo=findVal(amount,"busNo")
//        binding.busNoTV.text=busNo

        val currentStopName=findVal(amount,"currentStopName")
        binding.currentStopNameTV.text=currentStopName

        val currentPassengerCount=findVal(amount,"currentPassengerCount")
        binding.currentPassengerCountTV.text=currentPassengerCount
        binding.redirectBusDispToBusSelectFine.setOnClickListener {
            val action=BusSelectionDispFragmentDirections.actionBusSelectionDispFragmentToBusSelectionFineFragment(amount)
            findNavController().navigate(action)
        }
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