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
suspend fun <S> ApiCall(call: Call<S>, activity: MainActivity): Pair<Boolean, Response<S>?> {

    val loading= Loading_Dialog(activity)
    loading.start()

    val navController = Navigation.findNavController(activity, R.id.nav_host_fragment)

    var flag: Boolean = false
    var res: Response<S>? = null
    var fail:Boolean=false
    var cd:Int=200
    var flag2:Boolean=false

    val job = CoroutineScope(Dispatchers.IO).async {
        var check: Boolean = false

        call.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                fail=false
                check = true
                loading.isDismiss()

                res = response

                if (response.code() >= 500) { cd=500 }
                else if (response.code() >= 400) { cd=400 }
                else if (response.code() >= 200)
                {
                        val key = "token"
                        flag = true
                }
                else
                {
                        Toast.makeText(
                            (MainActivity()),
                            "Enter correct Login Details",
                            Toast.LENGTH_SHORT
                        ).show()
                }

            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                check = true
                fail=true
                cd=1000
                loading.isDismiss()
            }
        })

        while (check == false) {
//            Log.d("Check1", "in check + check=${check}")
        }

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

    return Pair(fail, res)
}
