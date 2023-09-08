package com.chalo.fieldauditapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:CreateAuditAPI by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://06d7-49-43-2-209.ngrok-free.app/fieldAudit/")
            .build()
            .create(CreateAuditAPI::class.java)
    }
}