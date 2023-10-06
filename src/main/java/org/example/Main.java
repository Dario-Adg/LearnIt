package org.example;

import back.Lesson;
import back.Module;
import back.ModuleLesson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello world!");
        Lesson lesson = new Lesson("test", "test");

        Module module = new Module("name", "description", null);
            
        CreateModule ("name", "description", "null");
        
    }

    private static void CreateModule(String name, String description, String aNull) {
    }
}