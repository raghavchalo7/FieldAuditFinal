package com.chalo.fieldauditapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.databinding.FragmentLoginBinding
import com.chalo.fieldauditapp.model.UserPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int){

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int){

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
        binding.editTextUser.addTextChangedListener(mTextWatcher);
        binding.editTextPassword.addTextChangedListener(mTextWatcher);

        val retrofitbuilder=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val jsonPlaceHolderAPI=retrofitbuilder.create(JsonPlaceHolderAPI::class.java)

        val userpost=UserPost(1,1,"title","This is Body")

        val call=jsonPlaceHolderAPI.sendUserData(userpost)
        call.enqueue(object :Callback<UserPost>{
            override fun onResponse(call: Call<UserPost>, response: Response<UserPost>) {
                binding.codeTV.text=response.code().toString()
            }

            override fun onFailure(call: Call<UserPost>, t: Throwable) {
                Log.d("Data",t.toString())
                binding.codeTV.text=t.message.toString()
            }

        })


        // run once to disable if empty
        checkFieldsForEmptyValues();
        binding.redirectLoginToBusSelection.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_busSelectionFragment)
        }
        return binding.root
    }

}