package com.chalo.fieldauditapp

import com.chalo.fieldauditapp.model.UserPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface JsonPlaceHolderAPI {

    @POST("posts")
    fun sendUserData(
        //@Headers()
        @Body userPost:UserPost
    ):Call<UserPost>
}