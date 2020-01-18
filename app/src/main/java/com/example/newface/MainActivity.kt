package com.example.newface

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newface.databinding.ActivityMainBinding
import com.example.newface.view.login.LoginActivity
import com.example.newface.view.main.PostActivity
import com.example.newface.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkLogin : SharedPreferences = getSharedPreferences("checkLogin", Context.MODE_PRIVATE)

        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        observerViewModel()
        viewModel.CheckLogin(checkLogin)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, PostActivity::class.java))
            })

            onFailEvent.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            })
        }
    }
}
