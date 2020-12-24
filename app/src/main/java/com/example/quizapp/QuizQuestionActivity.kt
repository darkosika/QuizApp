package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Questions>? = null
    private var mselectedCurrentPosition : Int = 0
    private var mCorrectAnswers : Int = 0
    private var muserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        muserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()

        setQuestions()

        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestions() {

        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text="FINISH"
        }else{
            btn_submit.text="GO TO NEXT QUESTION"
        }
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max
        tv_questions.text = question!!.question
        image.setImageResource(question.image)
        tv_optionOne.text = question.optionOne
        tv_optionTwo.text = question.optionTwo
        tv_optionThree.text = question.optionThree
        tv_optionFour.text = question.optionFour
    }

    private fun defaultOptionsView () {

        val options = ArrayList<TextView>()
        options.add(0,tv_optionOne)
        options.add(1,tv_optionTwo)
        options.add(2,tv_optionThree)
        options.add(3,tv_optionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,R.drawable.default_order_border_bg
            )
        }
    }

    private fun answerView (answer : Int, drawableView : Int){
        when(answer){
            1 -> {
                tv_optionOne.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 -> {
                tv_optionTwo.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 -> {
                tv_optionThree.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 -> {
                tv_optionFour.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun selectedOptionView (tv : TextView , selectedOptionNum : Int){
        defaultOptionsView()
        mselectedCurrentPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,R.drawable.selected_order_border_bg
        )
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_optionOne -> {
                selectedOptionView(tv_optionOne,1)
            }
            R.id.tv_optionTwo -> {
                selectedOptionView(tv_optionTwo,2)
            }
            R.id.tv_optionThree -> {
                selectedOptionView(tv_optionThree,3)
            }
            R.id.tv_optionFour -> {
                selectedOptionView(tv_optionFour,4)
            }
            R.id.btn_submit -> {
                if(mselectedCurrentPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestions()
                        }else -> {
                        val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME,muserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mselectedCurrentPosition){
                        answerView(mselectedCurrentPosition,R.drawable.wrong_order_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_order_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text= "FINISH"
                    }else{
                        btn_submit.text= "GO TO NEXT QUESTION"
                    }
                    mselectedCurrentPosition = 0
                }
            }
        }
    }
}