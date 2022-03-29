package com.example.final_project_eecs_1022;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addWordFragment extends Fragment {

    public addWordFragment() {}

    public static addWordFragment newInstance() {
        addWordFragment fragment = new addWordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_word, container, false);
        EditText getWord = (EditText) root.findViewById(R.id.enterWordToAdd);
        EditText getWordType = (EditText) root.findViewById(R.id.enterWordTypeToAdd);
        EditText getWordDefinition = (EditText) root.findViewById(R.id.enterWordDefinition);
        Button getWordValues = (Button) root.findViewById(R.id.addWordToDictionary);
        getWordValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getWord.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "You did not enter a word", Toast.LENGTH_SHORT).show();
                }
                if (getWordType.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "You did not enter a word type", Toast.LENGTH_SHORT).show();
                }
                if (getWordDefinition.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "You did not enter a word definition", Toast.LENGTH_SHORT).show();
                }
                if (getWordDefinition.getText().toString().trim().length() > 0 && getWordType.getText().toString().trim().length() > 0 && getWord.getText().toString().trim().length() > 0) {
                    String getWordString = getWord.getText().toString();
                    String getWordTypeString = getWordType.getText().toString();
                    String getWordDefinitionString = getWordDefinition.getText().toString();
                    System.out.println(getWordString);
                    System.out.println(getWordTypeString);
                    System.out.println(getWordDefinitionString);
                    String[] addWordArray = {getWordString, getWordTypeString, getWordDefinitionString};
                    Brain.Add(addWordArray, getContext());
                    Toast.makeText(getActivity(), "Added Word Successfully!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(root).popBackStack();
                }
            }
        });
        return root;
    }
}