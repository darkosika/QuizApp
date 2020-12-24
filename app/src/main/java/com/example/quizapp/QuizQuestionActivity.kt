package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val questionList = Constants.getQuestions()
        Log.i("Question Size is: ","${questionList.size}")

        val currentPosition = 1
        val question : Questions? = questionList[currentPosition-1]

        progressBar.progress = currentPosition
        tv_progress.text = "$currentPosition" + "/" +progressBar.max
        tv_questions.text = question!!.question
        image.setImageResource(question.image)
        tv_optionOne.text = question.optionOne
        tv_optionTwo.text = question.optionTwo
        tv_optionThree.text = question.optionThree
        tv_optionFour.text = question.optionFour
    }
}