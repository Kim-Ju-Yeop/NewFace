package com.project.meals.network

import com.google.gson.annotations.SerializedName

class Data {
//    var schoolList : List<SearchSchool>? = null

    @SerializedName("x-access-token")
    lateinit var token : String
}