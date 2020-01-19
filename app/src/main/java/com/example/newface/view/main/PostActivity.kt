package com.example.newface.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.newface.R
import com.example.newface.databinding.ActivityPostBinding
import com.example.newface.view.main.fragment.*
import com.example.newface.viewmodel.main.PostViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Member

class PostActivity : AppCompatActivity() {

    lateinit var binding : ActivityPostBinding
    lateinit var viewModel : PostViewModel

    val fragmentManager = supportFragmentManager

    val questionFragment = QuestionFragment()
    val memberFragment = MemberFragment()
    val codeReviewFragment = CodeReviewFragment()
    val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        binding = DataBindingUtil.setContentView(this@PostActivity, R.layout.activity_post)
        viewModel = ViewModelProviders.of(this@PostActivity).get(PostViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        init()
        selectedEvent()
    }

    fun init(){
        val fragmentTransCation = fragmentManager.beginTransaction()
        fragmentTransCation.replace(R.id.layout, questionFragment)
        fragmentTransCation.commit()
    }

    fun selectedEvent(){
        binding.bottomNavigationViewMainMenu.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                val fragmentTransaction = fragmentManager.beginTransaction()
                when (item.itemId) {
                    R.id.action_one -> {
                        fragmentTransaction.replace(R.id.layout, questionFragment)
                        fragmentTransaction.commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_two -> {
                        fragmentTransaction.replace(R.id.layout, memberFragment)
                        fragmentTransaction.commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_three -> {
                        fragmentTransaction.replace(R.id.layout, codeReviewFragment)
                        fragmentTransaction.commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_four -> {
                        fragmentTransaction.replace(R.id.layout, accountFragment)
                        fragmentTransaction.commit()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }

    override fun onBackPressed() {
        ActivityCompat.finishAffinity(this)
    }
}
