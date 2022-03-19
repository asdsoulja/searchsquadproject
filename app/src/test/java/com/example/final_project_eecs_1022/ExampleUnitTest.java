package com.example.final_project_eecs_1022;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;


public class ExampleUnitTest {
    @Test
    public void SearchTest(){
        ArrayList[] test = Brain.Search("Zoanthus");
        assertEquals(test[0].get(0),"A genus of Actinaria, including numerous species, found mostly in tropical seas. The zooids or polyps resemble small, elongated actinias united together at their bases by fleshy stolons, and thus forming extensive groups. The tentacles are small and bright colored.");
    }
    @Test
    public void AddTest(){
        String[] toAdd = new String[]{"Misha", "N.", "Someone who is awesome"};
        Brain.Add(toAdd);
        ArrayList[] test = Brain.Search("Misha");
        assertEquals(test[0].get(0),"Someone who is awesome");
    }

}