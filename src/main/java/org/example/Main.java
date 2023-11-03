package org.example;

import dataBaseSQL.*;
import back.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.Admin.MenuAdmin;
import static org.example.User.MenuUser;

public class Main {
    public static void main(String[] args) {
        MenuLoginInscription();
    }

    public static void MenuLoginInscription(){
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------MENU-----------");
            System.out.println("1. Connexion ");
            System.out.println("2. Inscription");
            System.out.println("3. Quitter");
            System.out.println("--------------------------");
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                // Gérer une entrée non numérique
                System.out.println("Veuillez entrer un chiffre.");
                scan.nextLine(); // Nettoyer le tampon d'entrée
                choice = 0; // Réinitialiser le choix pour éviter une boucle infinie
                continue;
            }

            switch (choice) {
                case 1 -> LogIn();
                case 2 -> Inscription();
                case 3 -> System.out.println("A bientôt");
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choice != 3);
        System.exit(0);
    }

    public static void LogIn(){
        Scanner scan = new Scanner(System.in);

        String login;
        do {
            System.out.println("Renseignez votre login");
            login = scan.nextLine();
            if (login.isBlank()){
                System.out.println("Login invalide ");
            }
        } while (login.isBlank());

        //String login = scan.nextLine();



        //String password = scan.nextLine();
        String password;
        do {
            System.out.println("Renseignez votre mot de passe");
            password = scan.nextLine();
            if (password.isBlank()){
                System.out.println("mdp invalide ");
            }
        } while (password.isBlank());



        User user = UserSQL.AuthenticateUser(login, password);
        if (user == null){
            System.out.println("Erreur aucun utilisateur avec cette email ou mot de passe");
        } else if (user.getIsAdmin()){
            MenuAdmin();
        } else{
            MenuUser();
        }
    }

    public static void Inscription(){
        Scanner scan = new Scanner(System.in);

        String email;
        do {
            System.out.println("Renseignez votre email");
            email = scan.nextLine();
            if (email.isBlank()){
                System.out.println("L'email est vide merci de renseignez un nouvelle email ");
            }
        } while (email.isBlank());



        //String password = scan.nextLine();

        String password;
        do {
            System.out.println("Renseignez votre mot de passe");
            password = scan.nextLine();
            if (password.isBlank()){
                System.out.println("mdp est vide merci de renseignez un nouvelle mdp ");
            }
        } while (password.isBlank());



        //String firstName = scan.nextLine();

        String firstName;
        do {
            System.out.println("Renseignez votre prenom");
            firstName = scan.nextLine();
            if (firstName.isBlank()){
                System.out.println("veuiller renseignez votre prenom ");
            }
        } while (firstName.isBlank());


        //String lastName = scan.nextLine().toUpperCase();

        String lastName;
        do {
            System.out.println("Renseignez votre Nom");
            lastName = scan.nextLine();
            if (lastName.isBlank()){
                System.out.println("veuiller renseignez votre Nom ");
            }
        } while (lastName.isBlank());

        String dayOfBirth = whileForDateOfBirth(scan, "Renseignez votre jour de naissance", false, false);
        String monthOfBirth = whileForDateOfBirth(scan, "Renseignez votre mois de naissance", true, false);
        String yearOfBirth = whileForDateOfBirth(scan, "Renseignez votre année de naissance",false, true);

        String dateOfBirth = dayOfBirth + monthOfBirth + yearOfBirth;

        int choice;
        boolean isJobSeeker = false;
        do{
            System.out.println("Etes-vous demandeur d'emplois");
            System.out.println("1. Oui ");
            System.out.println("2. Non");
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                // Gérer une entrée non numérique
                System.out.println("Veuillez entrer un chiffre.");
                scan.nextLine(); // Nettoyer le tampon d'entrée
                choice = 0; // Réinitialiser le choix pour éviter une boucle infinie
                continue;
            }
            switch (choice) {
                case 1 -> isJobSeeker = true;
                case 2 -> {}
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choice == 0);

        UserSQL.AddUser(email, password, firstName, lastName, isJobSeeker, dateOfBirth);
    }

    private static String whileForDateOfBirth(Scanner scan, String firstSentence,boolean isMonth, boolean isYear){
        boolean whileContinue = true;
        String dayMonthOrYearString = "";
        int numberMin = 1;
        int numberMax = 31;
        if (isMonth){
            numberMax = 12;
        }
        if (isYear){
            numberMin = 1990;
            numberMax = 2008;
        }
        do {
            System.out.println(firstSentence);
            if (scan.hasNextInt()){
                int dayMonthOrYearInt = scan.nextInt();
                if (dayMonthOrYearInt < numberMin){
                    System.out.println("Merci de renseigner un nombre valide au dessus de " + numberMin);
                } else if (dayMonthOrYearInt > numberMax) {
                    System.out.println("Merci de renseigner un nombre valide en dessous de " + numberMax);
                } else {
                    if (dayMonthOrYearInt <= 9){
                        dayMonthOrYearString = "0" + Integer.toString(dayMonthOrYearInt);
                    } else {
                        dayMonthOrYearString = Integer.toString(dayMonthOrYearInt);
                    }
                    if (isYear && dayMonthOrYearString.length() != 4){
                        System.out.println("Merci de renseigner quatres nombres ex: 1976 ou 1998 ou 2005");
                    }
                    else if (!isYear && dayMonthOrYearString.length() != 2){
                        System.out.println("Merci de renseigner deux nombres ex: 01 ou 20 ou 31");
                    } else {
                        whileContinue = false;
                    }
                }
            }else{
                System.out.println("Merci de renseigner des chiffres");
                scan.next();
            }
        } while (whileContinue);
        return dayMonthOrYearString;
    }
}