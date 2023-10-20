package org.example;

import back.Lesson;
import back.Module;
import back.Program;

import java.util.*;


public class Main {
    public static void main(String[] args) {


        List module = new ArrayList();
        module.add("First Module");

        List lesson = new ArrayList();
        lesson.add("First Lesson");

        Program program1 = new  Program("dhdfg", "sgfdfgdrgf", module, lesson);


        System.out.println(program1.SelectModule("2"));

    }
}
