package org.example;

import dataBaseSQL.UserSQL;

import java.util.Scanner;

import static org.example.Main.MenuLoginInscription;
import static org.example.User.MenuUser;

public class Admin {
    public static void MenuAdmin(){
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("--------MENU ADMIN--------");
            System.out.println("1. Liste ");
            System.out.println("2. Ajout ");
            System.out.println("3. Modification ");
            System.out.println("4. Déconnexion");
            System.out.println("--------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> MenuAdminList();
                case 2 -> MenuAdminAdd();
                case 3 -> MenuAdminUpdate();
                case 4 -> MenuLoginInscription();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 4);
    }

    public static void MenuAdminList(){
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("--------MENU LISTE--------");
            System.out.println("1. Utilisateurs");
            System.out.println("2. Parcours ");
            System.out.println("3. Modules ");
            System.out.println("4. Cours");
            System.out.println("5. Sortir");
            System.out.println("--------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> GetUsers();
                case 2 -> GetPrograms();
                case 3 -> GetModules();
                case 4 -> GetLessons();
                case 5 -> MenuAdmin();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 5);
    }

    public static void MenuAdminAdd(){
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("--------MENU AJOUT--------");
            System.out.println("1. Parcours ");
            System.out.println("2. Module ");
            System.out.println("3. Cours ");
            System.out.println("4. Sortir");
            System.out.println("--------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> AddProgram();
                case 2 -> AddModule();
                case 3 -> AddLesson();
                case 4 -> MenuAdmin();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 4);
    }

    public static void MenuAdminUpdate(){
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("-----MENU MODIFICATION-----");
            System.out.println("1. User ");
            System.out.println("2. Parcours ");
            System.out.println("3. Module ");
            System.out.println("4. Cours");
            System.out.println("5. Sortir");
            System.out.println("---------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> UpdateUser();
                case 2 -> UpdateProgram();
                case 3 -> UpdateModule();
                case 4 -> UpdateLesson();
                case 5 -> MenuAdmin();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 5);
    }

    public static void GetUsers(){
        Scanner scan = new Scanner(System.in);
    }
    public static void GetPrograms(){
        Scanner scan = new Scanner(System.in);
    }
    public static void GetModules(){
        Scanner scan = new Scanner(System.in);
    }
    public static void GetLessons(){
        Scanner scan = new Scanner(System.in);

    }
    public static void AddProgram(){
        Scanner scan = new Scanner(System.in);

    }
    public static void AddModule(){
        Scanner scan = new Scanner(System.in);

    }
    public static void AddLesson(){
        Scanner scan = new Scanner(System.in);

    }
    public static void UpdateUser(){
        Scanner scan = new Scanner(System.in);

    }
    public static void UpdateProgram(){
        Scanner scan = new Scanner(System.in);

    }
    public static void UpdateModule(){
        Scanner scan = new Scanner(System.in);

    }
    public static void UpdateLesson(){
        Scanner scan = new Scanner(System.in);

    }
}