package com.example.final_project_eecs_1022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class dictionaryHomePage extends Fragment {

    public dictionaryHomePage() {}

    public static dictionaryHomePage newInstance() {
        dictionaryHomePage fragment = new dictionaryHomePage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dictionary_home_page, container, false);
    }
}