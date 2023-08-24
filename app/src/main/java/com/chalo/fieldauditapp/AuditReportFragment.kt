package com.chalo.fieldauditapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalo.fieldauditapp.databinding.FragmentAuditReportBinding
import com.chalo.fieldauditapp.model.AuditReportRequestItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuditReportFragment : Fragment() {

    private var _binding: FragmentAuditReportBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAuditReportBinding.inflate(inflater,container, false)

        val call=RetrofitInstance.api.getAuditReports()
        var resp:ArrayList<AuditReportRequestItem>? = null
        val routeList=ArrayList<ItemViewsModel>()
        var sr:Int?=null
        call.enqueue(object : Callback<ArrayList<AuditReportRequestItem>> {
            override fun onResponse(call: Call<ArrayList<AuditReportRequestItem>>, response: Response<ArrayList<AuditReportRequestItem>>) {
                Log.d("Success2api",response.code().toString())
//                binding.code2TV.text=response.code().toString()
                val responseBody=response.body()!!
                resp=responseBody
                Toast.makeText(context,"Recieved Data="+responseBody[0].tripNumber,Toast.LENGTH_LONG).show()
                //Toast.makeText(context, "responseBody="+responseBody.toString(),Toast.LENGTH_LONG).show()
                val sz=responseBody.size
                Log.d("Success2apiDATA",sz.toString())


                for(i in 0..sz-1)
                {
                    val rt1=ItemViewsModel(responseBody[i].tripNumber,responseBody[i].auditEndBusStopId.toString())
                    routeList.add(rt1)
                }
                Log.d("DATAInonResponse",routeList.toString())
                sr=responseBody[0].auditStartBusStopId
                val itemAdapter=CustomAdapter(responseBody)
                binding.recyclerview.layoutManager= LinearLayoutManager(context)
                binding.recyclerview.adapter=itemAdapter
                Log.d("DATALIst",routeList.toString())

                itemAdapter.onItemClick={
                    Toast.makeText(context, "Pressed", Toast.LENGTH_LONG).show()
                    val  bottomSheetDialog: BottomSheetDialog =  BottomSheetDialog(requireContext())
                    bottomSheetDialog.setContentView(R.layout.auditdetails)

                    val busTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.busTV2a)
                    if (busTV2a != null) {
                        busTV2a.text=it.id.toString()
                    }

                    val routeTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.routeTV2a)
                    if (routeTV2a != null) {
                        routeTV2a.text=it.tripNumber
                    }

                    val stopTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.stopTV2a)
                    if (stopTV2a != null) {
                        stopTV2a.text=it.auditEndBusStopId.toString()
                    }

//                    val passengerTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.passengerTV2a)
//                    if (passengerTV2a != null) {
//                        passengerTV2a.text=it.passengerCount.toString()
//                    }

                    val fineCountTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.fineCountTV2a)
                    if (fineCountTV2a != null) {
                        fineCountTV2a.text=it.totalFinesCollected.toString()
                    }

                    val fineTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.fineTV2a)
                    if (fineTV2a != null) {
                        fineTV2a.text=it.totalFinesCount.toString()
                    }

//                    val passengerTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.passengerTV2a)
//                    if (passengerTV2a != null) {
//                        passengerTV2a.text=responseBody[0].passengerCount.toString()
//                    }

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

            }

            override fun onFailure(call: Call<ArrayList<AuditReportRequestItem>>, t: Throwable) {
                Log.d("Error2api",t.toString())
            }

        })

        //Toast.makeText(context,"Recieved Data="+sr,Toast.LENGTH_LONG).show()
        Log.d("DATAOutOfonResponse",resp.toString())

//HEREEEE
        //Recyclerview
        //val routelist= RouteConstant.getRouteData()
//        val routeList=ArrayList<ItemViewsModel>()
//        if(resp!=null) {
//            for (el in resp!!) {
//                val rt1 = ItemViewsModel(el.tripNumber, el.auditEndBusStopId.toString())
//                routeList.add(rt1)
//            }
//            val itemAdapter = CustomAdapter(routeList)
//            binding.recyclerview.layoutManager = LinearLayoutManager(context)
//            binding.recyclerview.adapter = itemAdapter
//
//            itemAdapter.onItemClick={
//                Toast.makeText(context, "Pressed", Toast.LENGTH_LONG).show()
//                val  bottomSheetDialog: BottomSheetDialog =  BottomSheetDialog(requireContext())
//                bottomSheetDialog.setContentView(R.layout.auditdetails)
////            val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.redirectBusSelectFineToBusDetailsDone)
////            if (b2 != null) {
////                b2.setOnClickListener {
////                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
//                val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.dimissAudit)
//                if (b2 != null) {
//                    b2.setOnClickListener {
//                        bottomSheetDialog.dismiss()
//                    }
//                }
////                }
////            }
//                bottomSheetDialog.show()
//            }
//        }
//        else
//        {
//            Toast.makeText(context,"else",Toast.LENGTH_LONG).show()
//        }
//
//
//
//        //from here
//
//        val routeList=ArrayList<ItemViewsModel>()
//        val rt1=ItemViewsModel(resp[0].tripNumber,"Vashi junction - Andheri east")
//        routeList.add(rt1)
//        val rt2=ItemViewsModel("32","Vashi junction - Andheri east")
//        routeList.add(rt2)
//        val rt3=ItemViewsModel("97","Vashi junction - Andheri east")
//        routeList.add(rt3)

        //to here




//        Insert here itemTouch

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}