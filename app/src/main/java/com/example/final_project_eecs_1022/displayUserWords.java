package com.example.final_project_eecs_1022;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class displayUserWords extends Fragment implements AdapterView.OnItemClickListener {

    ListView lvView;
    View root;
    public String wordClicked;

    public displayUserWords() {}

    public static displayUserWords newInstance(String param1, String param2) {
        displayUserWords fragment = new displayUserWords();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_display_user_words, container, false);
        lvView = root.findViewById(R.id.lvWords);
        String[] newStrings = ((MainActivity) getActivity()).getDictionaryArray();
        ArrayAdapter<String> lvdata = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,newStrings);
        lvView.setAdapter(lvdata);
        lvView.setOnItemClickListener(this);
        this.root = root;
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String wordClicked = adapterView.getItemAtPosition(i).toString();
        Bundle bundle = new Bundle();
        bundle.putString("wordClicked",wordClicked);
        Navigation.findNavController(root).navigate(R.id.action_displayUserWords_to_displayUserWordDefinition,bundle);
        System.out.println(wordClicked);

    }
}