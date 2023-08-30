package com.chalo.fieldauditapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Base64.encodeToString
//import java.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.chalo.fieldauditapp.databinding.ActivityMainBinding
import com.chalo.fieldauditapp.databinding.FragmentBusSelectionBinding
import com.chalo.fieldauditapp.model.CreateAuditNew
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class BusSelectionFragment : Fragment() {

    val TRANSFORMATION_ECB="AES/ECB/PKCS5Padding"

    val SPEC_ALGORITHM_AES="AES"

    val DECRYPT_MODE = 2


    lateinit var drawerLayout2: DrawerLayout

//    lateinit var toggle: ActionBarDrawerToggle
    private var _binding: FragmentBusSelectionBinding?=null
    private val binding get() = _binding!!

//    private var _binding2: ActivityMainBinding?=null
//    private val binding2 get() = _binding2!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBusSelectionBinding.inflate(inflater,container, false)


//        activity.gea().setDisplayHomeAsUpEnabled(true);
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        var token= sharedPreferences?.getString("token",null)


        token= token!!.substring(1, token!!.length-1);
        val response = RetrofitInstance.api.getAuditReports(token!!,false)
        Log.d("Token=",token)

        response.enqueue(object : Callback<CreateAuditNew> {
            override fun onResponse(
                call: Call<CreateAuditNew>,
                response: Response<CreateAuditNew>
            ) {
                Log.d("Resp=",response.code().toString())
                if(response.code()>=500)
                {
                    Toast.makeText(context,"ServerError",Toast.LENGTH_LONG).show()
                }
                else if(response.code()>=400)
                {
                    Toast.makeText(context,"Please try again: "+response.body(),Toast.LENGTH_LONG).show()

                }
                else if(response.code()>=200) {

                    val responseBody1 = response.body()!!


                    val responseBody=responseBody1.data.summary
                binding.totalBusesTV.text=responseBody.totalAudits.toString()
                binding.passengerCaughtTV.text=responseBody.passengerCaught.toString()
                binding.fineCollectionTV.text=responseBody.totalCollection.toString()
                }
            }

            //                    override fun onFailure(call: Call<String>, t: Throwable) {
            //                        Log.d("Errorapi1",t.toString())
            //                        //binding.code2TV.text=t.message.toString()
            //                    }

            override fun onFailure(
                call: Call<CreateAuditNew>,
                t: Throwable
            ) {
                Toast.makeText(context,"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show()
                Log.d("resp=",t.toString())
                TODO("Not yet implemented")
            }

        })

        binding.redirectBusSelectToBusSelectDisp.setOnClickListener {
//            IntentIntegrator().initiateScan()


            IntentIntegrator.forSupportFragment(this).initiateScan();
        }





        //From here for main
//        (activity as MainActivity?)!!.nview.setNavigationItemSelectedListener {
//
//            Log.d("TAG1","5")
//            when(it.itemId) {
////                R.id.audit -> Toast.makeText(context,"Audit Button Pressed.............",
////                    Toast.LENGTH_SHORT).show()
//
////                R.id.nav_host_fragment
//                R.id.audit -> findNavController().navigate(R.id.auditReportFragment)
//                R.id.logout -> {
//                        Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show()
//                    //logout()
////                                    val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
////                                    val editor= sharedPreferences?.edit()
////                                    editor?.remove("token")?.apply()
////
////                                    activity?.let{
////                                        val intent = Intent (it, SplashScreenActivity::class.java)
////                                        it.startActivity(intent)
////                                        (activity as AppCompatActivity).finish()
////                                    }
//
//                }
//            }
//            true
//        }

        //To here for main







//        val toolbar:androidx.appcompat.widget.Toolbar?=null
//        toolbar=

        //drawerLayout2 = binding.drawerLayout   ALSO COMMENTED
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toggle = ActionBarDrawerToggle((activity as AppCompatActivity),drawerLayout2,R.string.open,R.string.close)
//        drawerLayout2.addDrawerListener(toggle)
//        toggle.syncState()

//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        _binding!!.navView.setNavigationItemSelectedListener {
//            Log.d("TAG1","5")
//            when(it.itemId) {
////                R.id.audit -> Toast.makeText(context,"Audit Button Pressed.............",
////                    Toast.LENGTH_SHORT).show()
//                R.id.audit -> findNavController().navigate(R.id.action_busSelectionFragment_to_auditReportFragment)
//                R.id.logout -> {
//
//                    (activity as MainActivity?)!!.logout()
////                                    val sharedPreferences = activity?.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
////                                    val editor= sharedPreferences?.edit()
////                                    editor?.remove("token")?.apply()
////
////                                    activity?.let{
////                                        val intent = Intent (it, SplashScreenActivity::class.java)
////                                        it.startActivity(intent)
////                                        (activity as AppCompatActivity).finish()
////                                    }
//
//                                }
//            }
//            true
//        }

//        binding.redirectBusSelectToBusSelectDisp.setOnClickListener {
//            findNavController().navigate(R.id.action_busSelectionFragment_to_busSelectionDispFragment)
//        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setLogo(R.drawable.chalomasterlogo)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayUseLogoEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayUseLogoEnabled(false)
        //(activity as MainActivity?)?.setDrawerEnabled(false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val tsLong = System.currentTimeMillis() / 1000
                val ts = tsLong.toString()
                Toast.makeText(context, "Scanned at: " + ts, Toast.LENGTH_LONG).show()
                var dataResult1Previous=result.toString()
                //Toast.makeText(context,"Encrypted=${dataResult1Previous}",Toast.LENGTH_LONG).show()
                //val encoded = Base64.encode(dataResult1Previous.toByteArray(),Base64.NO_WRAP)
                val v1data=findVal(dataResult1Previous,"v1")
                Log.e("v1dat",v1data)
                val encodedByte=v1data.toByteArray()
                //Log.d("QRData",v1data)
                val secret="qwtPhBPjlpTpoPS7"
                val sec=Base64.encodeToString(secret.toByteArray(),Base64.NO_WRAP)
                //val secretBase64= Base64.encode(secret,Base64.NO_WRAP)

                val dataResult1ByteArray=decryptWithECB(encodedByte, sec)
                //val decodeResult=Base64.decode(dataResult1ByteArray,Base64.NO_WRAP)
//                Log.d("QRData",String(dataResult1ByteArray!!))
                val dataResult1=String(dataResult1ByteArray!!)
                Toast.makeText(context,"Decrypted=${dataResult1}",Toast.LENGTH_LONG).show()
                val dataResult= "$ts@$dataResult1"
                Log.d("QRData",dataResult1)
                val action=BusSelectionFragmentDirections.actionBusSelectionFragmentToBusSelectionDispFragment(dataResult)
                findNavController().navigate(action)

            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item))
//        {
//            return true
//        }
        return super.onOptionsItemSelected(item)
    }

    fun decryptWithECB(strToDecrypt:ByteArray, secret: String?): ByteArray? {

        try {
            secret?:return null // No Key found

            val base64Key = Base64.decode(secret, Base64.NO_WRAP)
            val secretKey = SecretKeySpec(base64Key, SPEC_ALGORITHM_AES)
            val cipher = Cipher.getInstance(TRANSFORMATION_ECB)
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return cipher.doFinal(Base64.decode(strToDecrypt,Base64.NO_WRAP))
        }catch (e:Exception) {
            Log.e("Decryption Error",e.toString())
            return null
        }
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