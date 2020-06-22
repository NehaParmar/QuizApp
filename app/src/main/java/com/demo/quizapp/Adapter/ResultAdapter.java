package com.demo.quizapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.quizapp.Model.Question;
import com.demo.quizapp.R;

import java.util.ArrayList;
import java.util.List;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Question> questions;
    private ArrayList<String> answerList;
    private String TAG = this.getClass().getSimpleName();

    public ResultAdapter(Activity context, List<Question> questions, ArrayList<String> answerList) {
        this.context = context;
        this.questions = new ArrayList<>(questions);
        this.answerList = new ArrayList<>(answerList);
    }

    @NonNull
    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, final int position) {
        Question mReview = questions.get(position);

        holder.txtQuestion.setText(mReview.getQuestion());
        holder.txtOpt1.setText(mReview.getOption1());
        holder.txtOpt2.setText(mReview.getOption2());
        holder.txtOpt3.setText(mReview.getOption3());
        holder.txtOpt4.setText(mReview.getOption4());

        holder.txtGiven.setText(answerList.get(position));
        String answer = "";
        if (mReview.getAnswerNum() == 1)
            answer = mReview.getOption1();
        else if (mReview.getAnswerNum() == 2)
            answer = mReview.getOption2();
        else if (mReview.getAnswerNum() == 3)
            answer = mReview.getOption3();
        else if (mReview.getAnswerNum() == 4)
            answer = mReview.getOption4();
        holder.txtCorrect.setText(answer);

    }


    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtQuestion, txtOpt1, txtOpt2, txtOpt3, txtOpt4, txtGiven, txtCorrect;

        public MyViewHolder(@NonNull View view) {
            super(view);
            txtQuestion = view.findViewById(R.id.txt_question);
            txtOpt1 = view.findViewById(R.id.tv_option1);
            txtOpt2 = view.findViewById(R.id.tv_option2);
            txtOpt3 = view.findViewById(R.id.tv_option3);
            txtOpt4 = view.findViewById(R.id.tv_option4);
            txtGiven = view.findViewById(R.id.tv_given);
            txtCorrect = view.findViewById(R.id.tv_correct);

//            ((MainActivity) context).changeDrawableColor(imgUser.getDrawable(), Global.appColors.getColorIcons());
        }
    }

}
