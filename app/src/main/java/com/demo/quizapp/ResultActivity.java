package com.demo.quizapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.quizapp.Adapter.ResultAdapter;
import com.demo.quizapp.Model.DBHelper;
import com.demo.quizapp.Model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    RecyclerView rvQuestions;
    ArrayList<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String answer = getIntent().getStringExtra("answers");
        String replace = answer.replace("[","");

        String finalString = replace.replace("]","");
        answers = new ArrayList<String>(Arrays.asList(finalString.split(",")));

        DBHelper dbHelper = new DBHelper(this);
        List<Question> questionList = dbHelper.getAllQuestions();

        ResultAdapter resultAdapter = new ResultAdapter(ResultActivity.this, questionList, answers);
        RecyclerView rvQuestions = findViewById(R.id.rvList);
        rvQuestions.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
        rvQuestions.setAdapter(resultAdapter);
    }
}
