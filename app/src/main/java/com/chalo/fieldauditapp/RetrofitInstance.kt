package com.chalo.fieldauditapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:CreateAuditAPI by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://c7e4-49-43-1-55.ngrok-free.app/field-audit/")
            .build()
            .create(CreateAuditAPI::class.java)
    }
}