package com.demo.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.demo.quizapp.Adapter.QuestionsPagerAdapter;
import com.demo.quizapp.Model.DBHelper;
import com.demo.quizapp.Model.Question;
import com.demo.quizapp.Utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private final String TAG = this.getClass().getSimpleName();

    private ViewPager viewPager;
    private Button btnNext;
    private TextView txtCountDown;
    private QuestionsPagerAdapter questionsAdapter;

    private List<Question> questionList = new ArrayList<>();
    private List<String> givenAnswers = new ArrayList<>();
    private int mPreviousPos = 0, score = 0;

    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DBHelper dbHelper = new DBHelper(this);
        questionList = dbHelper.getAllQuestions();

        Global.log(TAG, "Question count - " + questionList.size());

        init();
        setListener();

    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        btnNext = findViewById(R.id.btn_confirm_next);
        txtCountDown = findViewById(R.id.tv_countdown);

        questionsAdapter = new QuestionsPagerAdapter(getSupportFragmentManager());
        for (Question mQuestion : questionList) {
            questionsAdapter.addFragment(QuestionFragment.newInstance(mQuestion));
        }

        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(questionsAdapter);
        viewPager.setCurrentItem(mPreviousPos, false);
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void setListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = ((QuestionFragment) questionsAdapter.getItem(mPreviousPos)).getGivenAnswer();
                boolean answered = ((QuestionFragment) questionsAdapter.getItem(mPreviousPos)).isCorrectlyAnswered();
                if (answered)
                    score++;
                if (mPreviousPos < (questionList.size() - 1)) {
                    givenAnswers.add(answer);
                    Global.log(TAG,"Answer :"+givenAnswers.toString());
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                    if (mPreviousPos == (questionList.size() - 2))
                        btnNext.setText(getResources().getString(R.string.lbl_finish));

                    countDownTimer.cancel();
                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                    startCountDown();
                } else {
                    Global.log(TAG, "SCore - " + score);
                    givenAnswers.add(answer);
                    countDownTimer.cancel();
                    Global.log(TAG,"Answer :"+givenAnswers.toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                    builder.setView(getLayoutInflater().inflate(R.layout.dialog_message, null));
                    final Dialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    TextView txtMsg = dialog.findViewById(R.id.txtMessage);
                    Button btnSubmit = dialog.findViewById(R.id.btnOK);

                    txtMsg.setText(String.format("Your Score is %d / %d !", score, questionList.size()));
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                            intent.putExtra("answers", givenAnswers.toString());
                            startActivity(intent);

                            QuizActivity.this.finish();
                        }
                    });

                    btnNext.setClickable(false);
                }
            }
        });
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                btnNext.performClick();
//                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        txtCountDown.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            txtCountDown.setTextColor(Color.RED);
        } else {
            txtCountDown.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mPreviousPos = position;

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
