package com.example.newface.network.retrofit.interfaces

import com.example.newface.model.LoginPostRequest
import com.example.newface.model.RegisterPostRequest
import com.project.meals.network.Data
import com.project.meals.network.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Register {
    @POST("/auth/register")
    fun getRegister(@Body registerPostRequest : RegisterPostRequest) : Call<Response<Data>>
}