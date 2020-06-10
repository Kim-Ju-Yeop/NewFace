package com.example.newface.network.retrofit.interfaces

import com.example.newface.request.PostRequest
import com.project.meals.network.Data
import com.project.meals.network.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Post {

    @POST("/post")
    fun postLetter(@Header("x-access-token") token : String, @Body postRequest : PostRequest)  : Call<Response<Data>>
}