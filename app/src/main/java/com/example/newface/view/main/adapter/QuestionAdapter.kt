package com.example.newface.view.main.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newface.R
import com.example.newface.model.adapter.Question
import com.example.newface.model.answer.Posts

class QuestionAdapter(val questionList : ArrayList<Question>, val context : Context) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder = holder

        val listener = View.OnClickListener {
            Toast.makeText(context, "hello" + position, Toast.LENGTH_SHORT).show()
        }

        viewHolder.name.text = questionList.get(position).memberId
        viewHolder.title.text = questionList.get(position).title
        viewHolder.content.text = questionList.get(position).content

        if(questionList.get(position).files.equals("")){
            viewHolder.image.visibility = View.GONE
        } else{
            Glide.with(context).load("http://" + questionList.get(position).files).into(viewHolder.image)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val title : TextView = itemView.findViewById(R.id.title)
        val content : TextView = itemView.findViewById(R.id.content)
        val image : ImageView = itemView.findViewById(R.id.image)
    }
}