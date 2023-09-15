package com.chalo.fieldauditapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.chalo.fieldauditapp.model.CreateAuditRequest
import com.chalo.fieldauditapp.model.LoginRequest
import com.chalo.fieldauditapp.model.UserPost
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int){

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int){
            binding.editTextUserlayout.error=null

        }
        override fun afterTextChanged(editable: Editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues()
        }
    }

    fun checkFieldsForEmptyValues() {
        val s1: String = binding.editTextUser.getText().toString()
        val s2: String = binding.editTextPassword.getText().toString()
        if (s1 == "" || s2 == "") {
            binding.redirectLoginToBusSelection.alpha=0.4f
        } else {
            binding.redirectLoginToBusSelection.alpha=1f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentLoginBinding.inflate(inflater,container, false)
        (activity as MainActivity?)?.setDrawerEnabled(true)

        binding.editTextUser.addTextChangedListener(mTextWatcher);
        binding.editTextPassword.addTextChangedListener(mTextWatcher);

        //loadData()

//        val retrofitbuilder=Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .build()

//        val jsonPlaceHolderAPI=retrofitbuilder.create(JsonPlaceHolderAPI::class.java)

        val userpost=UserPost(1,1,"title","This is Body")

//        val call=jsonPlaceHolderAPI.sendUserData(userpost)
//        call.enqueue(object :Callback<UserPost>{
//            override fun onResponse(call: Call<UserPost>, response: Response<UserPost>) {
//                binding.codeTV.text=response.code().toString()
//            }
//
//            override fun onFailure(call: Call<UserPost>, t: Throwable) {
//                Log.d("Data",t.toString())
//                binding.codeTV.text=t.message.toString()
//            }
//
//        })


        // run once to disable if empty
        checkFieldsForEmptyValues();
        binding.redirectLoginToBusSelection.setOnClickListener {
            val s1: String = binding.editTextUser.getText().toString()
            val s2: String = binding.editTextPassword.getText().toString()
            val loginRequest=LoginRequest(password = s2, username = s1);
            val call=RetrofitInstance.api.getLoginToken(loginRequest)

            val loading=Loading_Dialog(activity as MainActivity)
            loading.start()

            //From here

            //send loginRequest, i.e. body  may send Header (T/F), get response, query -> (T/F) with the body if it's True, call response type)
            //if response code==200, then we need control, so return a pair<flag,response>, if true erite code, if false handled automatically

            //val responseType: CreateAuditRequest? =null
            val resp=apiCall(loginRequest,false,null,false,null,call,null)



            //Till here


            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    loading.isDismiss()

                    Log.d("SuccessapiLog",response.code().toString())
                    Log.d("SuccessapiLog",response.message().toString())
                    Log.d("SuccessapiLog",response.body().toString())
                    if(response.code()>=500)
                    {
                        //Toast.makeText(context,"ServerError",Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
                    }
                    else if(response.code()>=400)
                    {
                        Toast.makeText(context,"Wrong details",Toast.LENGTH_LONG).show()
                        //findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment) //Remove
                        binding.editTextUserlayout.error="This User ID does not exist"
                    }
                    else
                    if(response.code()>=200)     //CORRECT THIS ***+++**********************
                    {
                        val token= response.body()?.get("token")
                        val key="token"
                        saveData(key,token.toString())
                        binding.editTextUserlayout.error=null
                        findNavController().navigate(R.id.action_loginFragment_to_busSelectionFragment)
                    }
                    else
                    {
                        Toast.makeText(context,"Enter correct Login Details",Toast.LENGTH_SHORT).show()
                    }
                    //binding.code2TV.text=response.code().toString()
                    //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.d("ErrorapiLog",t.toString())
                    loading.isDismiss()
                    //Toast.makeText(context,"NO INTERNET CONNECTION TO LOGIN 22",Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_noNetworkFragment)
                    //binding.code2TV.text=t.message.toString()
                }

            })
//            findNavController().navigate(R.id.action_loginFragment_to_busSelectionFragment)
        }
        return binding.root
    }

//    override fun onBackPressed() {
//        Toast.makeText(context,"cw",Toast.LENGTH_LONG).show()
//        (activity as MainActivity).finishAffinity()
//////        //finishAffinity(activity as MainActivity)
//////        (this as AppCompatActivity).finishAffinity()
//////        (this as AppCompatActivity).finish()
////        getActivity()?.supportFinishAfterTransition();
//    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as MainActivity?)?.setDrawerEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity?)?.setDrawerEnabled(true)
    }

    private fun saveData(key:String, token:String)
    {

        val sharedPreferences = activity?.getSharedPreferences("sharedprefs",Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply{
            putString(key,token)
        }?.apply()
        //Toast.makeText(context, "Data Saved",Toast.LENGTH_SHORT).show()
    }

    private fun loginCheck()
    {
        val sharedPreferences = activity?.getSharedPreferences("sharedprefs",Context.MODE_PRIVATE)
        val token= sharedPreferences?.getString("STRING_KEY1",null)
        if(token!=null)
        {

        }
    }


}