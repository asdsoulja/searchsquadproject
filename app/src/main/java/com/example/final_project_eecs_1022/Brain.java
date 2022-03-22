package com.example.final_project_eecs_1022;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;

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
    public static ArrayList[] Search(String str, Context context) {
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
                if (nextLine[0].equals(str)) {
                    types.add(nextLine[1]);
                    defintions.add(nextLine[2]);
                    checkInDictionary = true;
                }
            }
            if (!checkInDictionary) {
                System.out.println("Word not found in main dictionary, searching in user dictionary");
                FileInputStream userFile = context.openFileInput("user-dictionary.csv");
                InputStreamReader getUserDictionary = new InputStreamReader(userFile);
                CSVReader userFileReader = new CSVReader(getUserDictionary);
                while ((nextLine = userFileReader.readNext()) != null) {
                    if (nextLine[0].equals(str)) {
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

    public static ArrayList<String> getUserDictionaryAsArray(Context context) {

        ArrayList<String> userWords = new ArrayList<>();
        try {
            FileInputStream userFile = context.openFileInput("user-dictionary.csv");
            InputStreamReader getUserDictionary = new InputStreamReader(userFile);
            CSVReader readUserDictionary = new CSVReader(getUserDictionary);
            String[] nextLine;
            while ((nextLine = readUserDictionary.readNext()) != null) {
                userWords.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return userWords;
    }

    public static void Add(String[] toAdd, Context context) {
        CSVWriter writeToUserDictionary = null;
        try {
            File writeToTempDictionary = new File(context.getFilesDir()+"/user-dictionary-temp.csv");
            writeToUserDictionary = new CSVWriter(new FileWriter(writeToTempDictionary));
            FileInputStream getInputStream = context.openFileInput("user-dictionary.csv");
            InputStreamReader readDictionary = new InputStreamReader(getInputStream);
            CSVReader readingDictionary = new CSVReader(readDictionary);
            String[] nextLine;
            while ((nextLine = readingDictionary.readNext())!= null){
                writeToUserDictionary.writeNext(nextLine);
            }
            writeToUserDictionary.writeNext(toAdd);
            writeToUserDictionary.close();
            readingDictionary.close();
            File oldFile = new File (context.getFilesDir()+"/user-dictionary.csv");
            oldFile.delete();
            File newFile = new File (context.getFilesDir()+"/user-dictionary-temp.csv");
            newFile.renameTo(oldFile);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }


    }

}
