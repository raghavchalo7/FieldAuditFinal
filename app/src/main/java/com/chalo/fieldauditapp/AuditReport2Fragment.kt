package com.chalo.fieldauditapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalo.fieldauditapp.databinding.FragmentAuditReport2Binding
import com.chalo.fieldauditapp.databinding.FragmentAuditReportBinding
//import com.chalo.fieldauditapp.model.AuditReportRequestItem
import com.chalo.fieldauditapp.model.CreateAuditNew
//import com.chalo.fieldauditapp.model.CreateAuditNew
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class AuditReport2Fragment : Fragment() {

    private var _binding: FragmentAuditReport2Binding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAuditReport2Binding.inflate(inflater,container, false)
        (activity as MainActivity?)?.setDrawerEnabled(false)

        (activity as MainActivity).supportActionBar?.title = "Audit Report"
        val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        var token= sharedPreferences?.getString("token",null)
        Log.d("TOKEN",token!!)
        //val call=RetrofitInstance.api.getAuditReports("cbus1111")
//        val headers = HashMap<String, String>()
//        headers["Token"] = token
//        //val call=RetrofitInstance.api.getAuditReports()




        token= token!!.substring(1, token!!.length-1);
        val response = RetrofitInstance.api.getAuditReports(token,true)   //jhhjvjh*****
        Log.d("LOGREQ", response.request().toString())
        if (response.isCanceled == false) {
            Log.d("Msg2", "Success")
            //var resp: ArrayList<AuditReportRequestItem>? = null
            val routeList = ArrayList<ItemViewsModel>()
            var sr: Int? = null
            //                val okkHttpclient = OkHttpClient.Builder()
            //                    .addInterceptor(ChuckerInterceptor(requireContext()))
            //                    //.addInterceptor(AuthHeaderInterceptor(authenticator = authenticator))
            //                    .addInterceptor(
            //                        ChuckerInterceptor.Builder(requireContext())
            //                        .collector(ChuckerCollector(requireContext()))
            //                        .maxContentLength(250000L)
            //                        .redactHeaders(emptySet())
            //                        .alwaysReadResponseBody(false)
            //                        .build())
            ////                    .connectTimeout(30, TimeUnit.SECOND)
            ////                    .readTimeout(60, TimeUnit.SECOND)
            ////                    .writeTimeout(60, TimeUnit.SECOND)
            ////                    .callTimeout(60, TimeUnit.SECOND)
            ////                    .cache(Cache(dir,10 * 1024 * 1024)) //10MB
            //                    .build()
            val loading=Loading_Dialog(activity as MainActivity)
            loading.start()
            response.enqueue(object : Callback<CreateAuditNew> {
                override fun onResponse(
                    call: Call<CreateAuditNew>,
                    response: Response<CreateAuditNew>
                ) {
                    loading.isDismiss()

                    if (response.code() >= 500) {
                        //Toast.makeText(context, "ServerError", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_auditReport2Fragment_to_errorDetailsFragment)
                    } else if (response.code() >= 400) {
                        Toast.makeText(context, "Wrong details", Toast.LENGTH_LONG).show()
                    } else if (response.code() >= 200)     //CORRECT THIS ***+++**********************
                    {
                        Log.d("Successapi1", response.code().toString())
                        Log.d("Success2api", response.code().toString())
                        //                binding.code2TV.text=response.code().toString()
                        val responseBody1 = response.body()!!
                        val responseBody = responseBody1.data.lists
                        //val tot=responseBody1.su
                        //resp=responseBody
//                            Toast.makeText(
//                                context,
//                                "Recieved Data=" + responseBody[0].tripNumber,
//                                Toast.LENGTH_LONG
//                            ).show()
                        //Toast.makeText(context, "responseBody="+responseBody.toString(),Toast.LENGTH_LONG).show()
                        val sz = responseBody.size
                        Log.d("Success2apiDATA", sz.toString())


                        for (i in 0..sz - 1) {
                            val rt1 = ItemViewsModel(
                                responseBody[i].tripNumber,
                                responseBody[i].auditEndBusStopId.toString()
                            )
                            routeList.add(rt1)
                        }
                        Log.d("DATAInonResponse", routeList.toString())
                        //sr = responseBody[0].auditStartBusStopId
                        val itemAdapter = CustomAdapter(responseBody)
                        binding.recyclerview.layoutManager = LinearLayoutManager(context)
                        binding.recyclerview.adapter = itemAdapter
                        Log.d("DATALIst", routeList.toString())

                        val busCnt: TextView? = binding.busCnt
                        if (busCnt != null) {
                            busCnt.text = sz.toString()
                        }

                        val PassCntTV: TextView? = binding.PassCntTV
                        if (PassCntTV != null) {
                            PassCntTV.text = responseBody1.data.summary.passengerCaught.toString()
                        }

                        val fineColl: TextView? = binding.fineColl
                        if (fineColl != null) {
                            fineColl.text = responseBody1.data.summary.totalCollection.toString()
                        }

                        itemAdapter.onItemClick = {
                            //Toast.makeText(context, "Pressed", Toast.LENGTH_LONG).show()
                            val bottomSheetDialog: BottomSheetDialog =
                                BottomSheetDialog(requireContext())
                            //bottomSheetDialog.setContentView()
                            bottomSheetDialog.setContentView(R.layout.auditdetails)

                            val busTV2a: TextView? =
                                bottomSheetDialog.findViewById<TextView>(R.id.busTV2a)
                            if (busTV2a != null) {
                                busTV2a.text = it.busNo.toString()
                            }


                            val routeTV2a: TextView? =
                                bottomSheetDialog.findViewById<TextView>(R.id.routeTV2a)
                            if (routeTV2a != null) {
                                routeTV2a.text = it.tripNumber
                            }

                            val stopTV2a: TextView? =
                                bottomSheetDialog.findViewById<TextView>(R.id.stopTV2a)
                            if (stopTV2a != null) {
                                stopTV2a.text = it.routeName.toString()
                            }

                            //                    val passengerTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.passengerTV2a)
                            //                    if (passengerTV2a != null) {
                            //                        passengerTV2a.text=it.passengerCount.toString()
                            //                    }

                            val fineCountTV2a: TextView? =
                                bottomSheetDialog.findViewById<TextView>(R.id.fineCountTV2a)
                            if (fineCountTV2a != null) {
                                fineCountTV2a.text = it.totalFinesCount.toInt().toString()
                            }

                            val fineTV2a: TextView? =
                                bottomSheetDialog.findViewById<TextView>(R.id.fineTV2a)
                            if (fineTV2a != null) {
                                val fineWithRs="₹"+it.totalFinesCollected.toString()
                                fineTV2a.text = fineWithRs
                            }

                            //                    val passengerTV2a: TextView? =bottomSheetDialog.findViewById<TextView>(R.id.passengerTV2a)
                            //                    if (passengerTV2a != null) {
                            //                        passengerTV2a.text=responseBody[0].passengerCount.toString()
                            //                    }

                            //            val b2=bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.redirectBusSelectFineToBusDetailsDone)
                            //            if (b2 != null) {
                            //                b2.setOnClickListener {
                            //                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
                            val b2 =
                                bottomSheetDialog.findViewById<androidx.appcompat.widget.AppCompatButton>(
                                    R.id.dimissAudit
                                )
                            if (b2 != null) {
                                b2.setOnClickListener {
                                    bottomSheetDialog.dismiss()
                                }
                            }
                            //                }
                            //            }
                            bottomSheetDialog.show()
                        }
                        //binding.code2TV.text=response.code().toString()
                        //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
                    }

                    //                    override fun onFailure(call: Call<String>, t: Throwable) {
                    //                        Log.d("Errorapi1",t.toString())
                    //                        //binding.code2TV.text=t.message.toString()
                    //                    }
                }

                override fun onFailure(
                    call: Call<CreateAuditNew>,
                    t: Throwable
                ) {
                    //Toast.makeText(context,"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show()
                    loading.isDismiss()
                    findNavController().navigate(R.id.action_auditReport2Fragment_to_noNetworkFragment)
                }

            })
            } else {
                (activity as MainActivity?)!!.logout()
            }

            //Log.d("Successapi","Success")


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as MainActivity?)?.setDrawerEnabled(false)
        (activity as MainActivity).supportActionBar?.title = "Audit Report"
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity?)?.setDrawerEnabled(true)

    }


    override fun onDestroyView() {
        //(activity as MainActivity).supportActionBar?.title = ""
        super.onDestroyView()
        _binding=null
    }

}