package org.example;

import java.util.Scanner;

import dataBaseSQL.UserSQL;

import java.util.Scanner;

public class User {
    public static void MenuUser(){

        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("-----MENU UTILISATEUR-----");
            System.out.println("1. Voir ses parcours ");
            System.out.println("2. S'inscrire a un parcours");
            System.out.println("3. Quitter");
            System.out.println("--------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> GetUserPrograms();
                case 2 -> AddUserProgram();
                case 3 -> System.out.println("A bientôt");
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 3);
    }
    public static void GetUserPrograms(){
        Scanner scan = new Scanner(System.in);
    }
    public static void AddUserProgram(){
        Scanner scan = new Scanner(System.in);
    }
}