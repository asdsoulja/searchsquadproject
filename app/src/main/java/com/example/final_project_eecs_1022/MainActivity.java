package com.example.final_project_eecs_1022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigation;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.File.*;
import android.content.Context;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterDictionary(View view) {
        Navigation.findNavController(view).navigate(R.id.action_start_Page_to_dictionaryHomePage);
        CopyAssetsToInternalStorage();
    }

    public void getWord(View view) {
        EditText inputWord = findViewById(R.id.enterWord);
        ArrayList[] definitionArray;
        definitionArray = Brain.Search(inputWord.getText().toString(), getApplicationContext());
        for (int i=0; i<definitionArray.length; i++){
            System.out.println(definitionArray[i]);
        }

    }

    public void CopyAssetsToInternalStorage() {
        if (!isFilePresent()){
            try{
                AssetManager getDictionaryFromAsset = getAssets();
                InputStream input = getDictionaryFromAsset.open("dictionary.csv");

                String internalOutDir = getApplicationContext().getFilesDir().getAbsolutePath();
                File internalDictionary = new File(internalOutDir, "dictionary.csv");
                OutputStream outputDictionary = new FileOutputStream(internalDictionary);



                byte[] buffer = new byte[1024];
                int read;
                while((read = input.read(buffer)) != -1){
                    outputDictionary.write(buffer, 0, read);
                }
                input.close();
                outputDictionary.close();



            }catch (IOException exception){
                System.out.println(exception);
            }
        }else{
            System.out.println("File is present, do nothing");
        }
    }
    public boolean isFilePresent() {
        String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/" + "dictionary.csv";
        File file = new File(path);
        return file.exists();
    }
}