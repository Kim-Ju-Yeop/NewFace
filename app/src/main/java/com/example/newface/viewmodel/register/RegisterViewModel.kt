package com.example.newface.viewmodel.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newface.model.RegisterPostRequest
import com.project.meals.network.Data
import com.project.meals.network.NetRetrofit
import com.project.meals.network.response.Response
import com.project.meals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class RegisterViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val intro = MutableLiveData<String>()
    val kakao = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onOverloadEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun register(){
        val registerPostRequest = RegisterPostRequest(id.value.toString(), pw.value.toString(), name.value.toString(), email.value.toString(), intro.value.toString(), kakao.value.toString())

        val res : Call<Response<Data>> = neRetrofit.register.getRegister(registerPostRequest)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                if(response.code() == 200){
                   Log.e("Status[200]", "회원가입에 성공을 하였습니다.")
                   onSuccessEvent.call()
                }else if(response.code() == 409){
                    Log.e("Status[409]", "중복된 아이디가 존재합니다.")
                    onOverloadEvent.call()
                } else if(response.code() == 500){
                    Log.e("Status[500]", "회원가입에 실패를 하였습니다. 다시 부탁드립니다.")
                    onFailEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("서버 통신 X", "서버 통신 안됨")
            }
        })
    }
}