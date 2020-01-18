package com.project.meals.network

import com.example.newface.network.retrofit.interfaces.Login
import com.example.newface.network.retrofit.interfaces.QuestionList
import com.example.newface.network.retrofit.interfaces.Register
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit {
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.43.175:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val login : Login = retrofit.create(Login::class.java)
    val register : Register = retrofit.create(Register::class.java)
    val questionList : QuestionList = retrofit.create(QuestionList::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}