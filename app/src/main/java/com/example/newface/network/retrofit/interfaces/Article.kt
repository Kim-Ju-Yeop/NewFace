package com.example.newface.network.retrofit.interfaces

import com.project.meals.network.Data
import com.project.meals.network.response.Response
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Article {

    @Multipart
    @POST("/upload")
    fun uploadImage(@Part files: MultipartBody.Part)  : Call<Response<Data>>
}