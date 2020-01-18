package com.example.newface.viewmodel.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newface.model.RegisterPostRequest

class RegisterViewModel : ViewModel(){

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val intro = MutableLiveData<String>()
    val kakao = MutableLiveData<String>()

    fun register(){
        val registerPostRequest = RegisterPostRequest(id.value.toString(), pw.value.toString(), name.value.toString(), email.value.toString(), intro.value.toString(), kakao.value.toString())
    }
}