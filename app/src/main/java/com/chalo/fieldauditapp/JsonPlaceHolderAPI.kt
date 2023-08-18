package com.chalo.fieldauditapp

import com.chalo.fieldauditapp.model.UserPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JsonPlaceHolderAPI {

    @POST("posts")
    fun sendUserData(
        @Body userPost:UserPost
    ):Call<UserPost >
}