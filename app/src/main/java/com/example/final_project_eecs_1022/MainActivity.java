package com.example.final_project_eecs_1022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void enterDictionary(View view) {
        Navigation.findNavController(view).navigate(R.id.action_start_Page_to_dictionaryHomePage);
    }
}