package com.project.meals.network

import com.example.newface.network.retrofit.interfaces.*
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
    val article : Article = retrofit.create(Article::class.java)
    val post : Post = retrofit.create(Post::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}