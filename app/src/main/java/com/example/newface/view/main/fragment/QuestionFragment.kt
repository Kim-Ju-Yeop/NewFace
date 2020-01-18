package com.example.newface.view.main.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.newface.R
import com.example.newface.databinding.FragmentQuestionBinding
import com.example.newface.viewmodel.main.fragment.QuestionViewModel


class QuestionFragment : Fragment() {

    lateinit var binding : FragmentQuestionBinding
    lateinit var viewModel : QuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        viewModel = ViewModelProviders.of(this@QuestionFragment).get(QuestionViewModel::class.java)

        return binding.root
    }

}
