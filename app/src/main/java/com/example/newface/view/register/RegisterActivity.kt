package com.example.newface.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newface.R
import com.example.newface.databinding.ActivityRegisterBinding
import com.example.newface.view.login.LoginActivity
import com.example.newface.viewmodel.register.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var viewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)
        viewModel = ViewModelProviders.of(this@RegisterActivity).get(RegisterViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@RegisterActivity, Observer {
                Toast.makeText(this@RegisterActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            })
            onOverloadEvent.observe(this@RegisterActivity, Observer {
                Toast.makeText(this@RegisterActivity, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show()
            })
            onFailEvent.observe(this@RegisterActivity, Observer {
                Toast.makeText(this@RegisterActivity, "회원가입에 실패를 하였습니다. 확인을 부탁드립니다.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
