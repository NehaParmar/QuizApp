package com.demo.quizapp;

import androidx.fragment.app.Fragment;

abstract class BaseFragment extends Fragment {
    abstract void initialization();

    abstract void setListener();

    abstract void prepareView();


}
