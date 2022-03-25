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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addWordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addWordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addWordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addWordFragment newInstance(String param1, String param2) {
        addWordFragment fragment = new addWordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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