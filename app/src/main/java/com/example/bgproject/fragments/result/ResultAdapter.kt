package com.example.bgproject.fragments.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.model.Result

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {


    private var resultList = emptyList<Result>()

    class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionNumber: TextView = view.findViewById(R.id.questionNumber)
        val question: TextView = view.findViewById(R.id.question)
        val yourAnswer: TextView = view.findViewById(R.id.yourAnswer)
        val correctAnswer: TextView = view.findViewById(R.id.correctAnswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.result_list, parent, false)
        return ResultViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val resultItem = resultList[position]
        val context = holder.itemView.context
        holder.questionNumber.text = (position+1).toString()
        holder.question.text = resultItem.question
        holder.yourAnswer.text = context.getString(R.string.your_answer,resultItem.selectedOption)
        holder.correctAnswer.text = context.getString(R.string.correct_answer,resultItem.correctAnswer)
    }

    fun setData(response: List<Result>){
        this.resultList = response
        notifyDataSetChanged()
    }
}