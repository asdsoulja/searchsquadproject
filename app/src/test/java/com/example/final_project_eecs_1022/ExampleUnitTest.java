package com.example.final_project_eecs_1022;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;


public class ExampleUnitTest {
    Brain b = new Brain();
    @Test
    public void SearchTest(){
        ArrayList[] test = b.Search("Abandon");
        assertEquals(test[0].get(0),"To cast or drive out; to banish; to expel; to reject.");
    }

}