package com.example.final_project_eecs_1022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link displayUserWordDefinition#newInstance} factory method to
 * create an instance of this fragment.
 */
public class displayUserWordDefinition extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public displayUserWordDefinition() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment displayUserWordDefinition.
     */
    // TODO: Rename and change types and number of parameters
    public static displayUserWordDefinition newInstance(String param1, String param2) {
        displayUserWordDefinition fragment = new displayUserWordDefinition();
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