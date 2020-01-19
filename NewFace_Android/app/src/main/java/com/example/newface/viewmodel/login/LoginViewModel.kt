package com.example.newface.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newface.request.LoginPostRequest
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class LoginViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()
    val onServerFailEvent = SingleLiveEvent<Unit>()
    val onRegisterEvent = SingleLiveEvent<Unit>()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val token = MutableLiveData<String>()


    fun login(){
        val loginPostRequest = LoginPostRequest(id.value.toString(), pw.value.toString())
        val res : Call<Response<Data>> = neRetrofit.login.getLogin(loginPostRequest)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                if(response.code() == 200){
                    if(response.body()!!.data != null){
                        Log.e("Status[200]", "로그인을 성공하였습니다.")
                        token.value = response.body()!!.data!!.token

                        onSuccessEvent.call()
                    }
                }else if(response.code() == 401){
                    Log.e("Status[401]", "로그인을 실패하였습니다.")
                    onFailEvent.call()
                } else if(response.code() == 500){
                    Log.e("Status[500]", "서버 오류가 발생하였습니다.")
                    onServerFailEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("서버 통신 X", "서버 통신 안됨")
            }
        })
    }

    fun register(){
        onRegisterEvent.call()
    }
}