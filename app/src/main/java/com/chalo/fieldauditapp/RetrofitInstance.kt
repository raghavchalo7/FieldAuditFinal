package com.chalo.fieldauditapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:CreateAuditAPI by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api-stagingafcs.chalo.com/fieldAudit/")
            .build()
            .create(CreateAuditAPI::class.java)
    }
}