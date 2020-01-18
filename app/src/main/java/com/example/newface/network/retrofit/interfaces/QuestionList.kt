package com.example.newface.network.retrofit.interfaces

import com.project.meals.network.Data
import com.project.meals.network.response.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface QuestionList {
    @GET("/post")
    fun getQuestionList(@Header("x-access-token") token : String) : Call<Response<Data>>
}