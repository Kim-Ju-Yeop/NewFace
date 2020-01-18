package com.example.newface.view.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newface.R
import com.example.newface.databinding.ActivityLoginBinding
import com.example.newface.view.main.PostActivity
import com.example.newface.view.register.RegisterActivity
import com.example.newface.viewmodel.login.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this@LoginActivity).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@LoginActivity, Observer {

                // LoginData Setting
                val loginData = applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val loginData_editor = loginData.edit()

                loginData_editor.putBoolean("loginData", true)
                loginData_editor.commit()

                // Token Setting
                val token = applicationContext.getSharedPreferences("token", Context.MODE_PRIVATE)
                val token_editor = token.edit()

                token_editor.putString("token", viewModel.token.value)
                token_editor.commit()

                startActivity(Intent(this@LoginActivity, PostActivity::class.java))
            })
            onFailEvent.observe(this@LoginActivity, Observer {
                Toast.makeText(this@LoginActivity, "로그인을 실패하였습니다.", Toast.LENGTH_SHORT).show()
            })
            onServerFailEvent.observe(this@LoginActivity, Observer {
                Toast.makeText(this@LoginActivity, "서버에서 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            })
            onRegisterEvent.observe(this@LoginActivity, Observer {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            })
        }
    }
}
