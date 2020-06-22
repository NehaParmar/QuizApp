package com.demo.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.quizapp.Model.Question;

public class QuestionFragment extends BaseFragment {
    private static final String ARG_QUES = "question";

    private View view;
    private Context context;
    private Question mQuestion;

    private TextView txtQuestion, txtCountDown;//txtQuestionNum, txtScore, ;
    private CheckBox chkOption1, chkOption2, chkOption3, chkOption4;

    public QuestionFragment() {
    }

    public static QuestionFragment newInstance(Question mQuestion) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUES, mQuestion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mQuestion = (Question) getArguments().getSerializable(ARG_QUES);
        initialization();
        setListener();
        prepareView();
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }*/

    @Override
    void initialization() {
        context = getActivity();

//        txtQuestionNum = view.findViewById(R.id.tv_question_count);
//        txtScore = view.findViewById(R.id.tv_score);
        txtCountDown = view.findViewById(R.id.tv_countdown);

        txtQuestion = view.findViewById(R.id.tv_question);

        chkOption1 = view.findViewById(R.id.chk_option1);
        chkOption2 = view.findViewById(R.id.chk_option2);
        chkOption3 = view.findViewById(R.id.chk_option3);
        chkOption4 = view.findViewById(R.id.chk_option4);
    }

    @Override
    void setListener() {
        chkOption1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chkOption2.setChecked(false);
                    chkOption3.setChecked(false);
                    chkOption4.setChecked(false);
                }
            }
        });

        chkOption2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chkOption1.setChecked(false);
                    chkOption3.setChecked(false);
                    chkOption4.setChecked(false);
                }
            }
        });
        chkOption3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chkOption2.setChecked(false);
                    chkOption1.setChecked(false);
                    chkOption4.setChecked(false);
                }
            }
        });
        chkOption4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chkOption2.setChecked(false);
                    chkOption3.setChecked(false);
                    chkOption1.setChecked(false);
                }
            }
        });
    }

    @Override
    void prepareView() {
        if (mQuestion != null) {
            txtQuestion.setText(mQuestion.getQuestion());

            chkOption1.setText(mQuestion.getOption1());
            chkOption2.setText(mQuestion.getOption2());
            chkOption3.setText(mQuestion.getOption3());
            chkOption4.setText(mQuestion.getOption4());

//            questionCounter++;
//            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
        }
    }

    public boolean isCorrectlyAnswered() {
        boolean isAnswered = false;
        if (chkOption1.isChecked() || chkOption2.isChecked() || chkOption3.isChecked() || chkOption4.isChecked()) {
            int answerKey = mQuestion.getAnswerNum();
            switch (answerKey) {
                case 1:
                    if (chkOption1.isChecked())
                        isAnswered = true;
                    break;
                case 2:
                    if (chkOption2.isChecked())
                        isAnswered = true;
                    break;
                case 3:
                    if (chkOption3.isChecked())
                        isAnswered = true;
                    break;
                case 4:
                    if (chkOption4.isChecked())
                        isAnswered = true;
                    break;
            }

            /*RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
            int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
            if (answerNr == currentQuestion.getAnswerNr()) {
                score++;
                textViewScore.setText("Score: " + score);
            }*/

        } else {
//            Toast.makeText(context, context.getResources().getString(R.string.msg_select_answer), Toast.LENGTH_SHORT).show();
        }
        return isAnswered;
    }

    public String getGivenAnswer() {
        String answer = "";
        if (chkOption1.isChecked())
            answer = mQuestion.getOption1();
        else if (chkOption2.isChecked())
            answer = mQuestion.getOption2();
        else if (chkOption3.isChecked())
            answer = mQuestion.getOption3();
        else if (chkOption4.isChecked())
            answer = mQuestion.getOption4();
        return answer;
    }

    /* public static QuestionFragment newInstance(Question mQuestion) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUES, mQuestion);
        fragment.setArguments(args);
        return fragment;
    }*/

    /* @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
             mParam1 = getArguments().getString(ARG_PARAM1);
             mParam2 = getArguments().getString(ARG_PARAM2);
         }
     }
 */


  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

   /* @Override
    public void onFragmentInteraction(Uri uri) {

    }*/

    /*
     *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
