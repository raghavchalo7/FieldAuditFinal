package com.chalo.fieldauditapp

import android.os.Bundle
import android.os.health.TimerStat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chalo.fieldauditapp.databinding.ConfirmdetailsBinding
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionFineBinding
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.chalo.fieldauditapp.model.CreateAuditRequest
import com.chalo.fieldauditapp.model.Fine
import com.chalo.fieldauditapp.model.UserPost
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException


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

        val waybill_number=findVal(amount,"waybillNo")

        val trip_number=findVal(amount,"tripNo")

//        val audit_start_bus_stop_id=findVal(amount,"currentStopId")

        val passenger_count=findVal(amount,"currentPassengerCount")

        val tm=amount.indexOf("@")
        val timeStart=amount.subSequence(0,tm).toString().toLong()

        val fines= ArrayList<Fine>()

        if(fineCount!=0)
        {
            binding.fineTV.visibility=View.VISIBLE
        }
        else
        {
            binding.fineTV.visibility=View.INVISIBLE
        }

        binding.issueFine.setOnClickListener {
            val ts = System.currentTimeMillis() / 1000
            val fineTs = ts.toString()

            val msg=binding.fineAmountET.text.toString()
            if(msg.trim().length>0) {
                fineCount = fineCount + 1
                val fine1: EditText? = binding.fineAmountET
                var fine: Int = 0
                if (fine1 != null) {
                    fine = fine1.text.toString().toInt()
                }
                val pair = Fine(fine,fineTs.toLong())
                fines.add(pair)

                binding.fineAmountET.getText().clear();
                fineColl = fineColl + fine
                binding.fineCountTV.text = fineCount.toString()
                binding.fineCollectedTV.text = fineColl.toString()
                binding.fineTV.visibility = View.VISIBLE
            }
            else
            {
                Toast.makeText(context, "Enter some fine", Toast.LENGTH_LONG).show()
            }

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
                    val tsLong = System.currentTimeMillis() / 1000
                    val timeEnd = tsLong.toString().toLong()
                    Toast.makeText(context, "Fines:"+fines, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_busSelectionFineFragment_to_busDetailsDoneFragment)
                    bottomSheetDialog.dismiss()


                    //URL is https://c7e4-49-43-1-55.ngrok-free.app/field-audit/create_field_audit' not https://jsonplaceholder.typicode.com/
//                    val retrofitbuilder= Retrofit.Builder()
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .baseUrl("https://c7e4-49-43-1-55.ngrok-free.app/field-audit/")
//                        .build()

                    val lt=Fine(20,232)
                    val lt2=Fine(50,532)
                    val lst= listOf<Fine>(lt,lt2)
//                    val auditReq=CreateAuditRequest(2,"2023-08-18T13:00:00Z",audit_start_bus_stop_id.toInt(),"2023-08-18T12:00:00Z",lst,passenger_count.toInt(),10.0,31,trip_number,waybill_number.toInt())

                    val audit_start_bus_stop_id=findVal(amount,"currentStopId")
                    println("audit_start_bus_stop_id is: $audit_start_bus_stop_id")
                    Log.d("Check","Passenger Count is: $passenger_count")
                    val auditReq=CreateAuditRequest(2,timeEnd,3,timeStart,fines,passenger_count.toInt(),10.0,31,"dw",10)


                    //val createAuditApi=retrofitbuilder.create(CreateAuditAPI::class.java)
                    //val userpost= UserPost(1,1,"title","This is Body")

                    //val lt= listOf<Fine>({110,1234567890})

                    //val call=createAuditApi.sendAuditData(auditReq)
                    val call=RetrofitInstance.api.sendAuditData(auditReq)

                    //From Here

//                    lifecycleScope.launchWhenCreated {
//                        Log.d("Msg","Emtering in the lifeCycleScope")
//                        val response=try{
//                            RetrofitInstance.api.sendAuditData(auditReq)
//                            //Log.d("Successapi","Success")
//                        } catch(e:IOException){
//                                Log.d("Errorapi1","IOException No Internet Connection")
//                            return@launchWhenCreated
//                        } catch (e:HttpException){
//                            Log.d("Errorapi1","HttpException No Internet Connection")
//                            return@launchWhenCreated
//                        }
//                        if(response.isExecuted)
//                        {
//                            Log.d("Msg2","Success")
//                            response.enqueue(object : Callback<String> {
//                                override fun onResponse(call: Call<String>, response: Response<String>) {
//                                    Log.d("Successapi1",response.code().toString())
//                                    binding.code2TV.text=response.code().toString()
//                                    //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
//                                }
//
//                                override fun onFailure(call: Call<String>, t: Throwable) {
//                                    Log.d("Errorapi1",t.toString())
//                                    binding.code2TV.text=t.message.toString()
//                                }
//
//                            })
//                        }
//                        else
//                        {
//                            Log.d("Errorapi","else")
//                        }
//                    }

                    //Till here






//                    Commented now
                    call.enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            Log.d("Successapi",response.code().toString())
                            binding.code2TV.text=response.code().toString()
                            //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.d("Errorapi",t.toString())
                            binding.code2TV.text=t.message.toString()
                        }

                    })

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