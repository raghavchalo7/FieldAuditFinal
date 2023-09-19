package com.chalo.fieldauditapp.ErrorHandling

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chalo.fieldauditapp.MainActivity
import com.chalo.fieldauditapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RequiresApi(Build.VERSION_CODES.R)
suspend fun <T, S> ApiCall(call: Call<S>, respType: String): Pair<Boolean, Response<S>?> {


    //val loading= Loading_Dialog(activity as MainActivity)
    //*loading.start()
//    ResourcesLoade
//    val url: URL = Launcher::class.java.getResource(name)
//    val gh:$respType
//    CreateAuditAPI as T
//    somethingThatReturnsInt() as T
    Log.d("Check1", "Before navHost")
    //val navHostFragment = MainActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    Log.d("Check1", "After navHost")
    var navController: NavController
    //navController = navHostFragment.findNavController()
    Log.d("Check1", "After navController")

    //val nvc=findNavController(R.id.nav_host_fragment)


//    val graphInflater = navHostFragment.navController.navInflater
//    val navGraph = graphInflater.inflate(R.navigation.nav_graph)
//    navController = navHostFragment.navController


    var flag: Boolean = false
    var res: Response<S>? = null


    //navController = MainActivity().navHostFragment.findNavController()
    //val resp:Pair<Boolean,Response<S>?>

    //val job=withContext(Dispatchers.Main){
    val job = CoroutineScope(Dispatchers.IO).async {
        Log.d("Check1", "Inside coroutine")
        var check: Boolean = false

        call.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                Log.d("Check1", "onResponse")
                check = true
                //*loading.isDismiss()

                Log.d("SuccessapiLog", response.code().toString())
                Log.d("SuccessapiLog", response.message().toString())
                Log.d("SuccessapiLog", response.body().toString())
                if (response.code() >= 500) {
                    //Toast.makeText(context,"ServerError",Toast.LENGTH_LONG).show()
                    //*NavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
                    //MainActivity().findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
                    //navController.navigate(R.id.errorDetailsFragment)
                    MainActivity().findNavController(R.id.errorDetailsFragment)
                    //return Pair(false, null)
                    //(Activity as MainActivity).
                } else if (response.code() >= 400) {
                    Toast.makeText((MainActivity()), "Wrong details", Toast.LENGTH_LONG).show()
                    //findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment) //Remove
                    //*binding.editTextUserlayout.error="This User ID does not exist"
                } else
                    if (response.code() >= 200)     //CORRECT THIS ***+++**********************
                    {
                        //*val token= response.body()?.get("token")
                        Log.d("Check1", "Inside 200 cond")
                        val key = "token"
                        flag = true
                        res = response
                        //MainActivity().findNavController(R.id.errorDetailsFragment)
                        //*saveData(key,token.toString())
                        //*binding.editTextUserlayout.error=null
                        //*findNavController().navigate(R.id.action_loginFragment_to_busSelectionFragment)
                    } else {
                        Toast.makeText(
                            (MainActivity()),
                            "Enter correct Login Details",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                //binding.code2TV.text=response.code().toString()
                //Toast.makeText(context, response.code(), Toast.LENGTH_LONG)
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                check = true
                Log.d("Check1", "onFailure")
                Log.d("ErrorapiLog", t.toString())
                //*loading.isDismiss()
                //Toast.makeText(context,"NO INTERNET CONNECTION TO LOGIN 22",Toast.LENGTH_LONG).show()
                //*findNavController().navigate(R.id.action_loginFragment_to_noNetworkFragment)
                //binding.code2TV.text=t.message.toString()
            }
        })
        Log.d("Check1", "At the end of Coroutine")
        while (check == false) {

        }
    }
    job.await()
    Log.d("Check1", "After Enqueue and flag=${flag}")


    return Pair(flag, res)
}

fun <T:Any> A(cx:Int,valw:T):Int
{
    return 2*cx
}
fun sj(cx:Int):Int
{
    return 2*cx
}