package com.example.final_project_eecs_1022;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class displaySearchDefinition extends Fragment {

    public displaySearchDefinition() {}

    public static displaySearchDefinition newInstance() {
        displaySearchDefinition fragment = new displaySearchDefinition();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_display_search_definition, container, false);
        ArrayList[] getSearchDefinitions = ((MainActivity)getActivity()).getSearchArray();
        TextView definitionTextView = root.findViewById(R.id.inputSearchDefinition);
        TextView wordTypeTextView = root.findViewById(R.id.searchDisplayWordType);
        definitionTextView.setText(String.valueOf(getSearchDefinitions[0].get(getArguments().getInt("getIndex"))));
        wordTypeTextView.setText(String.valueOf(getSearchDefinitions[1].get(getArguments().getInt("getIndex"))));
        Button backButton = root.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).popBackStack();
            }
        });
        return root;
    }
}