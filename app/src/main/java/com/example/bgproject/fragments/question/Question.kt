package com.example.bgproject.fragments.question

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    var isAnswered: Boolean = false,
    var selectedOptionPosition: Int = -1
)
