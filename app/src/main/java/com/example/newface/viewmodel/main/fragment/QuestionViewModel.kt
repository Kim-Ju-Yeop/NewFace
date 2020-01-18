package com.example.newface.viewmodel.main.fragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newface.model.adapter.Question
import com.example.newface.model.answer.Posts
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import java.lang.IndexOutOfBoundsException

class QuestionViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()
    val questionList = ArrayList<Question>()

    val onSuccessEvent = SingleLiveEvent<Unit>()

    fun questionList(tokenData : SharedPreferences){

        val token = tokenData.getString("token", null)

        val res : Call<Response<Data>> = neRetrofit.questionList.getQuestionList(token.toString())
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                Log.e("서버 통신 O", "서버 통신 됨")

                for(A in 0 until response.body()!!.data!!.posts!!.size){
                    val data : Posts = response.body()!!.data!!.posts!!.get(A)

                    try{
                        questionList.add(Question(data.title, data.content, data.memberId, data.files.get(0)))
                    }catch(e : IndexOutOfBoundsException){
                        questionList.add(Question(data.title, data.content, data.memberId, ""))
                    }
                }
                onSuccessEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("서버 통신 X", "서버 통신 안됨")
            }
        })
    }
}