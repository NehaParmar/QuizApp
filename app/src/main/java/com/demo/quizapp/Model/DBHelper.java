package com.demo.quizapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.quizapp.Model.NamingHelper.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COL_question + " TEXT, " +
                QuestionsTable.COL_OPTION1 + " TEXT, " +
                QuestionsTable.COL_OPTION2 + " TEXT, " +
                QuestionsTable.COL_OPTION3 + " TEXT, " +
                QuestionsTable.COL_OPTION4 + " TEXT, " +
                QuestionsTable.COL_ANSWER_NUM + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following is the national animal of India?", "Lion", "Tiger", "Elephant", "Rhinoceros", 2);
        addQuestion(q1);
        Question q2 = new Question("How many colours are there in India's National Flag", "Two", "Three", "Four", "Five", 2);
        addQuestion(q2);
        Question q3 = new Question("When do we celebrate our Independence Day?", "15th January", "26th January", "26th August", "15th August", 4);
        addQuestion(q3);
        Question q4 = new Question("Which of the following month have 31 days?", "October", "February", "June", "April", 1);
        addQuestion(q4);
        Question q5 = new Question("Which of the following is not an outdoor game?", "Cricket", "Football", "Hockey", "Table Tennis", 4);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
//        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COL_question, question.getQuestion());
        cv.put(QuestionsTable.COL_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COL_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COL_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COL_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COL_ANSWER_NUM, question.getAnswerNum());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COL_question)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COL_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COL_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COL_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COL_OPTION4)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuestionsTable.COL_ANSWER_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
