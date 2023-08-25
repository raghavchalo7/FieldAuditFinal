package com.chalo.fieldauditapp

import com.chalo.fieldauditapp.model.AuditReportRequestItem
import com.chalo.fieldauditapp.model.CreateAuditRequest
import com.chalo.fieldauditapp.model.LoginRequest
import com.chalo.fieldauditapp.model.LoginResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateAuditAPI {
    @POST("login")
    fun getLoginToken(
        @Body loginRequest: LoginRequest
    ):Call<JsonObject>

    @POST("create_field_audit")
    fun sendAuditData(
        @Body createAuditRequest: CreateAuditRequest
    ):Call<String>

    //@Headers("Token:cbus1111")
    @GET("audits-by-crew?crewId=1")
    fun getAuditReports(@Header("Token") token:String):Call<ArrayList<AuditReportRequestItem>>
    //@Header("Token") token:String
}