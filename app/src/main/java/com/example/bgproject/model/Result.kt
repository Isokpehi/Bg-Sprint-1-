package com.example.bgproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Result(

//    var tglId: String = "",
////    var questionNumber : Int = 0,
    var questionId: Int = 0,
    var question: String = "",
    var selectedOption: String = "",
    var correctAnswer: String = "",

    )

@Entity("response_table")
data class Response(
    @PrimaryKey
    var tglId: String = "",
    var result: List<Result>,
    var score: Int
)