package com.example.final_project_eecs_1022;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class displayUserWordDefinition extends Fragment {

    public displayUserWordDefinition() {}

    public static displayUserWordDefinition newInstance() {
        displayUserWordDefinition fragment = new displayUserWordDefinition();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_display_user_word_definition, container, false);
        TextView displayWord = (TextView) root.findViewById(R.id.displayWordClicked);
        TextView wordType = (TextView) root.findViewById(R.id.displayWordType);
        TextView wordDefinition = (TextView) root.findViewById(R.id.displayDefinition);
        displayWord.setText(getArguments().getString("wordClicked"));
        ArrayList [] getUserDefinition = Brain.searchInUserDictionary(getArguments().getString("wordClicked"),getContext());
        wordType.setText(String.valueOf((getUserDefinition[1].get(0))));

        wordDefinition.setText(String.valueOf((getUserDefinition[0].get(0))));
        wordDefinition.setMovementMethod(new ScrollingMovementMethod());
        Button removeButton = (Button) root.findViewById(R.id.removeWordButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Brain.removeWordFromUserDictionary(getArguments().getString("wordClicked"),getContext());
                Toast.makeText(getActivity(),"Removed Word",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(root).popBackStack();
                Navigation.findNavController(root).popBackStack();
            }
        });
        return root;
    }
}