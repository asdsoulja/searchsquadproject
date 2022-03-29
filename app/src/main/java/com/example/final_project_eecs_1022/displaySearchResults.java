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

public class displaySearchResults extends Fragment implements AdapterView.OnItemClickListener {
    ListView lvView;
    View root;

    public displaySearchResults() {}

    public static displaySearchResults newInstance() {
        displaySearchResults fragment = new displaySearchResults();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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