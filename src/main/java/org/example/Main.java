package org.example;

import back.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("-----------MENU-----------");
            System.out.println("1. Connexion ");
            System.out.println("2. Inscription");
            System.out.println("3. Quitter");
            System.out.println("--------------------------");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> LogIn();
                case 2 -> Inscription();
                case 3 -> System.out.println("Fin");
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 3);
    }

    public static void LogIn(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Renseignez votre login");
        String login = scan.nextLine();
        System.out.println("Renseignez votre mot de passe");
        String password = scan.nextLine();

        //User.LogIn(login, password);
    }

    public static void Inscription(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Renseignez votre login");
        String login = scan.nextLine();
        System.out.println("Renseignez votre mot de passe");
        String password = scan.nextLine();
        System.out.println("Renseignez votre prenom");
        String firstName = scan.nextLine();
        System.out.println("Renseignez votre nom");
        String lastName = scan.nextLine();
        System.out.println("Etes-vous demandeur d'emplois (oui/non)");
        String jobSeeker = scan.nextLine();
        boolean isJobSeeker = false;
        if (jobSeeker.equals("oui")){
            isJobSeeker = true;
        }
        System.out.println("Renseignez votre date de naissance (yyyy-mm-dd)");
        String dateOfBirth = scan.nextLine();

        User.AddUser(login, password, firstName, lastName, isJobSeeker,dateOfBirth);
        System.out.println("Utilisateur créer avec succès");
    }
}