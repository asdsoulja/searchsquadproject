package com.example.final_project_eecs_1022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    String[] dictionaryArray;
    ArrayList[] searchArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videoclip);
        videoView.start();
    }

    @Override
    public void onResume() {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        super.onResume();
        videoView.resume();
    }

    @Override
    public void onPause() {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        super.onPause();
        videoView.suspend();
    }

    public void enterDictionary(View view) {
        Navigation.findNavController(view).navigate(R.id.action_start_Page_to_dictionaryHomePage);

        CopyAssetsToInternalStorage();
    }
    public void enterAddWordFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_dictionaryHomePage_to_addWordFragment);
    }

    public void enterSearchField(View view) {
        Navigation.findNavController(view).navigate(R.id.action_dictionaryHomePage_to_displaySearchResults);
    }

    public void displayUserWords(View view) {
        Navigation.findNavController(view).navigate(R.id.action_dictionaryHomePage_to_displayUserWords);

        ArrayList<String> getUserDict = Brain.getUserDictionaryAsArray(getApplicationContext());
        dictionaryArray = new String[getUserDict.size()];
        for (int i=0; i< dictionaryArray.length; i++){
            dictionaryArray[i] = getUserDict.get(i);
        }
        for (int i=0; i< dictionaryArray.length; i++){
            System.out.println(dictionaryArray[i]);
        }

    }


    public void getWord(View view) {
        EditText inputWord = findViewById(R.id.enterWord);
        String getInput = inputWord.getText().toString();
        this.searchArray = Brain.Search(getInput,getApplicationContext());
        if (this.searchArray[0].size() == 0){
            Toast.makeText(this, "Unable to find word in database", Toast.LENGTH_SHORT).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("input",getInput);
            Navigation.findNavController(view).navigate(R.id.action_dictionaryHomePage_to_displaySearchResults,bundle);
        }
    }

    public void CopyAssetsToInternalStorage() {
        if (!checkUserDictionary()){
            System.out.println("User Dictionary missing! Creating new one....");
            String internalDir = getApplicationContext().getFilesDir().getAbsolutePath();
            File createUserDictionary = new File(internalDir,"user-dictionary.csv");
            try {
                createUserDictionary.createNewFile();
                String[] addExample = {"Example Word","EX","This is a example word"};
                for (int i=1; i<5; i++){
                    String[] addExampleTemp = addExample.clone();
                    addExampleTemp[0] = addExampleTemp[0] + " " + String.valueOf(i);
                    addExampleTemp[2] = addExampleTemp[2] + " " + String.valueOf(i);
                    Brain.Add(addExampleTemp, getApplicationContext());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("User Dictionary is present, do nothing");
        }
        if (!isFilePresent()){
            try{
                System.out.println("Database not found in internal storage! Copying assets...");
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
                System.out.println("Asset copy complete");
            }catch (IOException exception){
                System.out.println(exception);
            }
        }else{
            System.out.println("Main Dictionary is present, do nothing");
        }
    }
    public boolean isFilePresent() {
        String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/" + "dictionary.csv";
        File file = new File(path);
        return file.exists();
    }
    public boolean checkUserDictionary(){
        String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/"+"user-dictionary.csv";
        File file =  new File(path);
        return file.exists();
    }

    public String[] getDictionaryArray(){
        return dictionaryArray;
    }
    public ArrayList[] getSearchArray(){
        return this.searchArray;
    }
}