package com.example.final_project_eecs_1022;
import android.view.View;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import android.os.Bundle;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import android.content.res.AssetManager;
import android.content.Context;
public class Brain {


    /*
        This method looks through the dictionary and adds all the defintions to one array list, and all the types (noun,verb,etc) to the other. Then it outputs them in an array.
        So if the word is "birth",

        RES[0].get(0)= "the emergence of a new individual from the body of its parent"
        RES[1].get(0) = N (Noun)

        If there is a different definition it can be accessed by

        RES[0].get(1) = "the act or process of bearing or bringing forth offspring; childbirth"
        RES[0].get(1) = N (Noun)

        If things are both adjectives and nouns this becomes useful.

     */
    public static ArrayList[] Search(String str, Context context){
        ArrayList<String> defintions = new ArrayList();
        ArrayList<String> types = new ArrayList();

        try {
            FileInputStream file = context.openFileInput("dictionary.csv");
            InputStreamReader getDictionary = new InputStreamReader(file);
            CSVReader reader = new CSVReader(getDictionary);
            String[] nextLine;
            boolean checkInDictionary = false;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if(nextLine[0].equals(str)){
                    types.add(nextLine[1]);
                    defintions.add(nextLine[2]);
                    checkInDictionary = true;
                }
            }
            if (!checkInDictionary){
                System.out.println("Word not found in main dictionary, searching in user dictionary");
                FileInputStream userFile = context.openFileInput("user-dictionary.csv");
                InputStreamReader getUserDictionary = new InputStreamReader(userFile);
                CSVReader userFileReader = new CSVReader(getUserDictionary);
                while((nextLine = userFileReader.readNext()) != null){
                    if (nextLine[0].equals(str)){
                        types.add(nextLine[1]);
                        defintions.add(nextLine[2]);
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        ArrayList[] RES = new ArrayList[2];
        RES[0] = defintions;
        RES[1] = types;
        return RES;
    }

    public static void Add(String[] toAdd, Context context){

        try {
            FileInputStream file = context.openFileInput("user-dictionary.csv");
            InputStreamReader getDictionary = new InputStreamReader(file);

            String internalOutDir = context.getFilesDir().getAbsolutePath();
            File internalDictionary = new File(internalOutDir, "user-dictionary.csv");

            CSVWriter writer = new CSVWriter(new FileWriter(internalDictionary));
            CSVReader reader = new CSVReader(getDictionary);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                    writer.writeNext(nextLine);
                }
            writer.writeNext(toAdd);
            writer.close();
            reader.close();

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
