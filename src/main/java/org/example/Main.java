package org.example;

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
        System.out.println("Etes-vous demandeur d'emplois");
        boolean jobSeeker = scan.nextBoolean();

        //User.LogIn(login, password);
    }
//    public static void GetProduct(){
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Renseignez l'id du produit ou son nom");
//        String name = scan.nextLine();
//        int id = 0;
//        try{
//            Integer.parseInt(name);
//            id = Integer.parseInt(name);
//            name = "";
//        } catch (NumberFormatException ignored){}
//
//        CheckResult(name, id);
//    }
//    public static void GetLogs(){
//        System.out.println("Interface des logs");
//        System.out.println("--------------------------");
//        CommandSQL.GetLogs();
//    }
//    public static void DeleteProduct(){
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Interface de suppression d'un produit");
//        System.out.println("--------------------------");
//        System.out.println("Renseignez l'id du produit ou son nom");
//        String name = scan.nextLine();
//        int id = 0;
//        try{
//            Integer.parseInt(name);
//            id = Integer.parseInt(name);
//            name = "";
//        } catch (NumberFormatException ignored){}
//
//        CommandSQL.DeleteProduct(name, id);
//    }
//    public static void AddProduct(){
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Interface de rajout d'un produit");
//        System.out.println("--------------------------");
//        System.out.println("Renseignez nom du produit");
//        String name = scan.nextLine();
//        System.out.println("Nom du produit : " + name );
//        System.out.println("Renseignez description du produit");
//        String description = scan.nextLine();
//        System.out.println("Nom du produit : " + name );
//        System.out.println("Description du produit : " + description );
//        System.out.println("Renseignez la quantité de ce produit :");
//        int quantity = scan.nextInt();
//        System.out.println("Nom du produit : " + name );
//        System.out.println("Description du produit : " + description );
//        System.out.println("Quantité de ce produit : " + quantity );
//
//        CommandSQL.AddProduct(name, description, quantity);
//    }
//    public static void UpdateProduct(){
//        Scanner scan = new Scanner(System.in).useDelimiter("\n");
//
//        System.out.println("Interface de modification d'un produit");
//        System.out.println("--------------------------");
//        System.out.println("Choisir le produit a modifier par l'id ou le nom");
//        String name = scan.nextLine();
//        int id = 0;
//        try{
//            Integer.parseInt(name);
//            id = Integer.parseInt(name);
//            name = "";
//        } catch (NumberFormatException ignored){}
//        if (CheckResult(name, id)){
//            int choice;
//            do {
//                System.out.println("Que voulez-vous modifier ?");
//                System.out.println("1. Name");
//                System.out.println("2. Description");
//                System.out.println("3. Quantité");
//                System.out.println("4. Quitter");
//                choice = scan.nextInt();
//
//                switch (choice) {
//                    case 1 -> {
//                        System.out.println("Choisir le nouveau nom");
//                        String newName = scan.next();
//                        CommandSQL.UpdateProduct(newName, "", "", name, id);
//                        name = newName;
//                    }
//                    case 2 -> {
//                        System.out.println("Choisir la nouvelle description");
//                        CommandSQL.UpdateProduct("", scan.next(), "", name, id);
//                    }
//                    case 3 -> {
//                        System.out.println("Choisir la nouvelle quantité");
//                        CommandSQL.UpdateProduct("", "", scan.next(), name, id);
//                    }
//                    case 4 -> System.out.println("Fin");
//                    default -> System.out.println("Le choix n'est pas valide");
//                }
//            } while (choice != 4);
//        }
//    }
//    private static boolean CheckResult(String name, int id){
//        boolean go = true;
//        String check = CommandSQL.GetProduct(name, id);
//        if (!check.isEmpty()){
//            System.out.println(check);
//            return go = false;
//        }
//        return go;
//    }
}