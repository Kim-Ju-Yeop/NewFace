package com.example.newface.viewmodel.main.article

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newface.request.PostRequest
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class ArticleViewModel : ViewModel() {

    val neRetrofit = NetRetrofit()

    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun write(token2 : SharedPreferences){

        val token = token2.getString("token", "")
        val postRequest = PostRequest(title.value.toString(), content.value.toString())

        val res = neRetrofit.post.postLetter(token.toString(), postRequest)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                onSuccessEvent.call()
            }

            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                onFailEvent.call()
            }
        })
    }
}