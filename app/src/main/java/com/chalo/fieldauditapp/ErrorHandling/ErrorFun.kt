package com.chalo.fieldauditapp.ErrorHandling

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.chalo.fieldauditapp.Loading_Dialog
import com.chalo.fieldauditapp.MainActivity
import com.chalo.fieldauditapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RequiresApi(Build.VERSION_CODES.R)
suspend fun <T, S> ApiCall(call: Call<S>, respType: String, activity: MainActivity): Pair<Boolean, Response<S>?> {


    val loading= Loading_Dialog(activity)
    loading.start()
//    ResourcesLoade
//    val url: URL = Launcher::class.java.getResource(name)
//    val gh:$respType
//    CreateAuditAPI as T
//    somethingThatReturnsInt() as T
    Log.d("Check1", "Before navHost")
    //val navHostFragment = MainActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    Log.d("Check1", "After navHost")
    //var navController: NavController
    //navController = navHostFragment.findNavController()
    Log.d("Check1", "After navController")
    val navController = Navigation.findNavController(activity, R.id.nav_host_fragment)

    //val nvc=findNavController(R.id.nav_host_fragment)


    //val graphInflater = navHostFragment.navController.navInflater
//    val navGraph = graphInflater.inflate(R.navigation.nav_graph)
//    navController = navHostFragment.navController


    var flag: Boolean = false
    var res: Response<S>? = null
    var fail:Boolean=false
    var cd:Int=200
    var flag2:Boolean=false

    //navController = MainActivity().navHostFragment.findNavController()
    //val resp:Pair<Boolean,Response<S>?>

    //val job=withContext(Dispatchers.Main){
    val job = CoroutineScope(Dispatchers.IO).async {
        Log.d("Check1", "Inside coroutine")
        var check: Boolean = false

        //check=false
        call.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                Log.d("Check1", "onResponse")
                fail=false
                check = true
                loading.isDismiss()

                res = response
                Log.d("SuccessapiLog", response.code().toString())
                Log.d("SuccessapiLog", response.message().toString())
                Log.d("SuccessapiLog", response.body().toString())
                if (response.code() >= 500) {
                    //Toast.makeText(context,"ServerError",Toast.LENGTH_LONG).show()
                    //*NavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
                    //MainActivity().findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment)
                    //navController.navigate(R.id.errorDetailsFragment)
                    cd=500
                    //activity.findNavController(R.id.errorDetailsFragment)
                    //return Pair(false, null)
                    //(Activity as MainActivity).
                } else if (response.code() >= 400) {
                    //Toast.makeText(activity , "Wrong details", Toast.LENGTH_LONG).show()
                    cd=400
                    //findNavController().navigate(R.id.action_loginFragment_to_errorDetailsFragment) //Remove
                    //*binding.editTextUserlayout.error="This User ID does not exist"
                } else if (response.code() >= 200)     //CORRECT THIS ***+++**********************
                    {
                        //*val token= response.body()?.get("token")
                        Log.d("Check1", "Inside 200 cond")
                        val key = "token"
                        flag = true

//                        res = response
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
                fail=true
                Log.d("Check1", "onFailure")
                Log.d("ErrorapiLog", t.toString())

                cd=1000

                //activity.findNavController(R.id.noNetworkFragment)
                //navController.navigate(R.id.noNetworkFragment)

                loading.isDismiss()
                //Toast.makeText(context,"NO INTERNET CONNECTION TO LOGIN 22",Toast.LENGTH_LONG).show()
                //*findNavController().navigate(R.id.action_loginFragment_to_noNetworkFragment)
                //binding.code2TV.text=t.message.toString()
            }
        })
        Log.d("Check1", "At the end of Coroutine + check=${check}")
        while (check == false) {
            Log.d("Check1", "in check + check=${check}")
        }
//        if(fail==true)
//        {
//            withContext(Dispatchers.Main){
//                //navController.navigate(R.id.noNetworkFragment)
//                //NavHostFragment.findNavController(R.navigation)
//                Log.d("Check1","In fail==true")
////                activity.findNavController(R.id.noNetworkFragment)
////                activity.
//                navController.navigate(R.id.noNetworkFragment)
//            }
//        }
        Log.d("Check1","*******")
        if(flag==false)
        {
            withContext(Dispatchers.Main)
            {
                when (cd) {
                    400 -> {
                        Log.d("Check1","400")
                        Toast.makeText(activity,"Wrong details",Toast.LENGTH_LONG).show()
                    }
                    500 -> {
                        Log.d("Check1","500")
                        navController.navigate(R.id.errorDetailsFragment)
                    }
                    1000 -> {
                        Log.d("Check1","1000")
                        navController.navigate(R.id.noNetworkFragment)
                    }
                    else -> {
                        Log.d("Check1","else")
                    }
                }
            }
        }
        flag2=flag
        flag=false
        check=false
    }
    job.await()
    Log.d("Check1", "After Enqueue and flag=${flag2}")


    return Pair(fail, res)
}

fun <T:Any> A(cx:Int,valw:T):Int
{
    return 2*cx
}
fun sj(cx:Int):Int
{
    return 2*cx
}