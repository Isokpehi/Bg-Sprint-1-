package com.example.bgproject.fragments.question

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.data.ResultTypeConverter
import com.example.bgproject.model.Result
import com.example.bgproject.viewmodel.UserViewModel

class QuestionAdapter(
    private val questionList: ArrayList<Question>,
    private val viewModel: UserViewModel,


) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {


    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionNumber: TextView = view.findViewById(R.id.questionNumber)
        val question: TextView = view.findViewById(R.id.question)
        val optionsRadioGroup: RadioGroup = view.findViewById(R.id.optionsRadioGroup)
        val option1RadioButton: RadioButton = view.findViewById(R.id.option1RadioButton)
        val option2RadioButton: RadioButton = view.findViewById(R.id.option2RadioButton)
        val option3RadioButton: RadioButton = view.findViewById(R.id.option3RadioButton)
        val option4RadioButton: RadioButton = view.findViewById(R.id.option4RadioButton)
        val option5RadioButton: RadioButton = view.findViewById(R.id.option5RadioButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.question_list, parent, false)
        return QuestionViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = questionList[position]
        holder.questionNumber.text = (position + 1).toString()
        holder.question.text = item.question
        holder.option1RadioButton.text = item.options[0]
        holder.option2RadioButton.text = item.options[1]
        holder.option3RadioButton.text = item.options[2]
        holder.option4RadioButton.text = item.options[3]
        holder.option5RadioButton.text = item.options[4]



        // Set the checked state of the RadioButton based on the selected option

        holder.optionsRadioGroup.clearCheck()



        if (item.selectedOptionPosition != -1) {
            val radioButton =
                holder.optionsRadioGroup.getChildAt(item.selectedOptionPosition) as RadioButton
            radioButton.isChecked = true
        }
// Set the click listener for each RadioButton
        // Set the click listener for each RadioButton
        holder.optionsRadioGroup.setOnCheckedChangeListener(null)
        holder.optionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedPosition = when (checkedId) {
                R.id.option1RadioButton -> 0
                R.id.option2RadioButton -> 1
                R.id.option3RadioButton -> 2
                R.id.option4RadioButton -> 3
                R.id.option5RadioButton -> 4
                else -> -1
            }
            if (selectedPosition != -1) {
                updateSelectedOption(holder,position, selectedPosition)
            }
        }

        holder.optionsRadioGroup.isEnabled = !item.isAnswered

    }


    private fun updateSelectedOption(
        holder: QuestionViewHolder,
        position: Int,
        selectedPosition: Int
    ) {
        viewModel.updateUserSelection(position, selectedPosition)
        questionList[position].selectedOptionPosition = selectedPosition
        questionList[position].isAnswered = true
    }


    fun getResults(): List<Result> {
        val results = mutableListOf<Result>()
        for (question in questionList) {
            if (question.isAnswered && question.selectedOptionPosition >= 0 && question.selectedOptionPosition < question.options.size) {
                val selectedOption = question.options[question.selectedOptionPosition]
                val correctAnswer = question.options[question.correctAnswer]
                val result = Result(
                    questionId = question.id,
                    question = question.question,
                    selectedOption = selectedOption,
                    correctAnswer = correctAnswer
                )
                results.add(result)
            }
        }
        return results
    }



}

