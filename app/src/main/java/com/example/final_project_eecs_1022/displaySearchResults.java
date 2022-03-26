package com.example.final_project_eecs_1022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link displaySearchResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class displaySearchResults extends Fragment implements AdapterView.OnItemClickListener {
    ListView lvView;
    View root;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public displaySearchResults() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment displaySearchResults.
     */
    // TODO: Rename and change types and number of parameters
    public static displaySearchResults newInstance(String param1, String param2) {
        displaySearchResults fragment = new displaySearchResults();
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
        View root = inflater.inflate(R.layout.fragment_display_search_results, container, false);


        ArrayList[] getDictionaryResult = ((MainActivity) getActivity()).getSearchArray();
        String[] displayAmountOfDefinitions = new String[getDictionaryResult[0].size()];
        for (int i=0; i<getDictionaryResult[0].size(); i++){
            displayAmountOfDefinitions[i] = "Tap me to see definition "+String.valueOf(i+1);
            System.out.println(displayAmountOfDefinitions[i]);
        }
        TextView displayStatus = (TextView) root.findViewById(R.id.multipleDefinitionsTextView);
        if (getDictionaryResult[0].size() == 1){
            displayStatus.setText("There seems to be one match. Tap to view it");
        }else{
            displayStatus.setText("Multiple Definitions! Tap one to view");
        }

        lvView = root.findViewById(R.id.displayResultsList);
        ArrayAdapter<String> lvdata = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,displayAmountOfDefinitions);
        lvView.setAdapter(lvdata);
        lvView.setOnItemClickListener(this);
        this.root = root;
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putInt("getIndex", i);
        System.out.println(bundle.get("getIndex"));
        Navigation.findNavController(root).navigate(R.id.action_displaySearchResults_to_displaySearchDefinition,bundle);
    }
}