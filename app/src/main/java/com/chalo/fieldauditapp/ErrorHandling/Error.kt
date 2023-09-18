package com.chalo.fieldauditapp.ErrorHandling

import android.content.res.loader.ResourcesLoader
import android.content.res.loader.ResourcesProvider
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.Loading_Dialog
import com.chalo.fieldauditapp.MainActivity
import com.chalo.fieldauditapp.R
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

@RequiresApi(Build.VERSION_CODES.R)
inline fun <T:Any> ApiCall(call: Call<Any>, respType: String):Pair<Boolean,T>
{

    //*val loading= Loading_Dialog(activity as MainActivity)
    //*loading.start()
    ResourcesLoade
    val url: URL = Launcher::class.java.getResource(name)
    val gh:$respType

    call.enqueue(object : Callback<$respType> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            //*loading.isDismiss()

            Log.d("SuccessapiLog",response.code().toString())
            Log.d("SuccessapiLog",response.message().toString())
            Log.d("SuccessapiLog",response.body().toString())
            if(response.code()>=500)
            {
                //Toast.makeText(context,"ServerError",Toast.LENGTH_LONG).show()
                //*NavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
            }
            else if(response.code()>=400)
            {
                //*Toast.makeText(context,"Wrong details", Toast.LENGTH_LONG).show()
                //findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment) //Remove
                //*binding.editTextUserlayout.error="This User ID does not exist"
            }
            else
                if(response.code()>=200)     //CORRECT THIS ***+++**********************
                {
                    val token= response.body()?.get("token")
                    val key="token"
                    //*saveData(key,token.toString())
                    //*binding.editTextUserlayout.error=null
                    //*findNavController().navigate(R.id.action_loginFragment_to_busSelectionFragment)
                }
                else
                {
                    //*Toast.makeText(context,"Enter correct Login Details", Toast.LENGTH_SHORT).show()
                }
            //binding.code2TV.text=response.code().toString()
            //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.d("ErrorapiLog",t.toString())
            //*loading.isDismiss()
            //Toast.makeText(context,"NO INTERNET CONNECTION TO LOGIN 22",Toast.LENGTH_LONG).show()
            //*findNavController().navigate(R.id.action_loginFragment_to_noNetworkFragment)
            //binding.code2TV.text=t.message.toString()
        }

    })


    val resp:Pair<Boolean,T> =Pair(false,null!!)
    return resp
}

fun <T:Any> A(cx:Int,valw:T):Int
{
    return 2*cx
}
fun sj(cx:Int):Int
{
    return 2*cx
}