package com.chalo.fieldauditapp.ErrorHandling

import retrofit2.Call
fun <T:Any, Req:Any> ApiCall(ReqBody:Req, headerFlag:Boolean, headerBody:Pair<String,String>, queryFlag:Boolean, queryBody:Pair<String,String>, call: Call<T>, respType:T):Pair<Boolean,T>
{

    val resp:Pair<Boolean,T> =Pair(false,null)
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