package com.example.newface.view.main.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.newface.R
import com.example.newface.databinding.FragmentQuestionBinding
import com.example.newface.view.main.PostActivity
import com.example.newface.view.main.adapter.QuestionAdapter
import com.example.newface.view.main.article.ArticleActivity
import com.example.newface.viewmodel.main.fragment.QuestionViewModel


class QuestionFragment : Fragment() {

    lateinit var binding : FragmentQuestionBinding
    lateinit var viewModel : QuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        viewModel = ViewModelProviders.of(this@QuestionFragment).get(QuestionViewModel::class.java)

        val token : SharedPreferences = context!!.getSharedPreferences("token", Context.MODE_PRIVATE)

        viewModel.questionList(token)
        observerViewModel()
        floatingOnclick()

        return binding.root
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@QuestionFragment, Observer {
                val adapter = QuestionAdapter(viewModel.questionList, binding.root.context)
                binding.recyclerView.adapter = adapter
            })
        }
    }

    fun floatingOnclick(){
        binding.floatingActionButton3.setOnClickListener(View.OnClickListener {
            binding.root.context.startActivity(Intent(binding.root.context, ArticleActivity::class.java))
        })
    }
}
