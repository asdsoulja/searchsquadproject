package com.example.final_project_eecs_1022;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Brain {


    /*
        This method looks through the dictionary and adds all the defintions to one array list, and all the types (noun,verb,etc) to the other. Then it outputs them in an array.
        So if the word is "birth",

        RES[0].get(0)= "the emergence of a new individual from the body of its parent"
        RES[1].get(0) = N (Noun)

        If there is a different defintion it can be acessed by

        RES[0].get(1) = "the act or process of bearing or bringing forth offspring; childbirth"
        RES[0].get(1) = N (Noun)

        If things are both adjectives and nouns this becomes useful.

     */
    public static ArrayList[] Search(String str){
        ArrayList<String> defintions = new ArrayList();
        ArrayList<String> types = new ArrayList();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/res/dictionary.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if(nextLine[0].equals(str)){
                    types.add(nextLine[1]);
                    defintions.add(nextLine[2]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("File not found");
        }
        ArrayList[] RES = new ArrayList[2];
        RES[0] = defintions;
        RES[1] = types;
        return RES;
    }



}
