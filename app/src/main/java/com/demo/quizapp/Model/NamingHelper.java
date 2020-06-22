package com.demo.quizapp.Model;

import android.provider.BaseColumns;

public final class NamingHelper {
    public NamingHelper() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COL_question = "question";
        public static final String COL_OPTION1 = "option1";
        public static final String COL_OPTION2 = "option2";
        public static final String COL_OPTION3 = "option3";
        public static final String COL_OPTION4 = "option4";
        public static final String COL_ANSWER_NUM = "answerNum";
//        public static final String COL_WAS_ANSWERED = "wasAnswered";
//        public static final String COL_WAS_CORRECT = "wasCorrect";
    }


}
