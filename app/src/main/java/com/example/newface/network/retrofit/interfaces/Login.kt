package com.example.newface.network.retrofit.interfaces

import com.example.newface.model.LoginPostRequest
import com.project.meals.network.Data
import com.project.meals.network.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("/auth/login")
    fun getLogin(@Body loginPostRequest : LoginPostRequest) : Call<Response<Data>>
}