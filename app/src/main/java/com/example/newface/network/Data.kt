package com.project.meals.network

import com.example.newface.model.answer.Files
import com.example.newface.model.answer.Posts
import com.google.gson.annotations.SerializedName

class Data {

    var posts : List<Posts>?= null
    val files : List<Files>?= null

    @SerializedName("x-access-token")
    lateinit var token : String
}