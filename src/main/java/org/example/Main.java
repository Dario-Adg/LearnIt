package org.example;

import back.Lesson;
import back.Module;
import back.Program;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        Module[] modules = new Module[5];
        Module module1 = new Module("fierstProgram");
        Module module2 = new Module("secondetProgram");
        modules[0] = module1;
        modules[1] = module2;





        Program program = new Program("1", "Denys", (List<Module>) modules[0], "pas de description");

    }
}
