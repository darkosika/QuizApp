package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"
    fun getQuestions():ArrayList<Questions> {
        val questionList = ArrayList<Questions>()
        val que1 = Questions(
            1,
            "what country does this flag belong to ?",
            R.drawable.ic_flag,
            "Greece",
            "Germany",
            "France",
            "Turkey",
            4)

        val que2 = Questions(
            1,
            "who is the football player in pic?",
            R.drawable.ic_cr7,
            "CR7",
            "Messi",
            "Figo",
            "Maradona",
            1)
        questionList.add(que1)
        questionList.add(que2)
        return questionList
    }
}