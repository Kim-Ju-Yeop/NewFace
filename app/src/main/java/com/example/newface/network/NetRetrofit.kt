package com.project.meals.network

import com.example.newface.network.retrofit.interfaces.Login
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit {
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.137.227:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val login : Login = retrofit.create(Login::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}