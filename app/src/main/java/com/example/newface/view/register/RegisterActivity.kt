package com.example.newface.view.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.newface.R
import com.example.newface.databinding.ActivityRegisterBinding
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
    }
}
