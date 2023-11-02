package org.example;

import back.Job;
import back.Program;
import back.UserProgram;
import dataBaseSQL.ProgramSQL;
import dataBaseSQL.UserSQL;
import back.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.Main.MenuLoginInscription;
import static org.example.User.MenuUser;

public class Admin {
    public static void MenuAdmin(){
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------MENU ADMIN--------");
            System.out.println("1. Utilisateurs");
            System.out.println("2. Parcours ");
            System.out.println("3. Modules ");
            System.out.println("4. Cours");
            System.out.println("5. Déconnexion");
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
                case 1 -> GetUsers();
                case 2 -> GetPrograms();
                case 3 -> GetModules();
                case 4 -> GetLessons();
                case 5 -> MenuLoginInscription();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choice != 5);
    }
    public static void GetUsers(){
        List<User> users = UserSQL.GetUsers();
        if (users.isEmpty()){
            System.out.println("Aucun utilisateur");
        } else {
            for (User user: users) {
                System.out.println("Id : " + user.getId());
                System.out.println("Prénom : " + user.getFirstName());
                System.out.println("Nom : " + user.getLastName());
                System.out.println("---------------------------------");
            }
            Scanner scan = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Souhaitez-vous sélectionner un utilisateur ?");
                System.out.println("1. Oui ");
                System.out.println("2. Non ");
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
                    case 1 -> {
                        int userId;
                        do{
                            System.out.println("Indiquer l'Id de l'utilisateur souhaité");
                            try {
                                userId = scan.nextInt();
                            } catch (InputMismatchException e) {
                                // Gérer une entrée non numérique
                                System.out.println("Veuillez entrer un chiffre.");
                                scan.nextLine(); // Nettoyer le tampon d'entrée
                                userId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                continue;
                            }
                            User user = UserSQL.GetUserByIdWithProgram(userId);
                            if (user != null){
                                System.out.println("Id : " + user.getId());
                                System.out.println("Prénom : " + user.getFirstName());
                                System.out.println("Nom : " + user.getLastName());
                                System.out.println("Date de naissance : " +
                                        new SimpleDateFormat("dd-MM-yyyy").format(user.getDateOfBirth()));
                                System.out.println("Chercheur d'emplois : " + (user.getIsJobSeeker() ? "Oui" : "Non"));
                                System.out.println(user.getDiplomaNumber() != 0 ?
                                        "Numéro diplôme : " + user.getDiplomaNumber() : "Pas encore de numéro de diplôme");
                                List<UserProgram> userPrograms = user.getUserPrograms();
                                if (!userPrograms.isEmpty()){
                                    System.out.println("Parcours suivi par " + user.getFirstNameLastName());
                                    for (UserProgram userProgram: userPrograms) {
                                        System.out.println("**");
                                        System.out.println("Id du parcours : " + userProgram.getProgram().getId());
                                        System.out.println("Nom du parcours : " + userProgram.getProgram().getName());
                                        System.out.println("Description du parcours : " +
                                                userProgram.getProgram().getDescription());
                                        boolean programValid = userProgram.isValid();
                                        System.out.println(programValid ? "Parcours validé" : "Parcours en cours");
                                        System.out.println(programValid ? "Parcours terminé" :
                                                "Fin limite du parcours : " + userProgram.getEndDateProgram());
                                        System.out.println("**");
                                    }
                                } else {
                                    System.out.println("Aucun parcours suivi par " + user.getFirstNameLastName());
                                }
                                System.out.println("---------------------------------");
                                int choiceForAdmin;
                                do {
                                    System.out.println("Souhaitez-vous passer admin " + user.getFirstNameLastName() + " ?");
                                    System.out.println("1. Oui ");
                                    System.out.println("2. Non ");
                                    try {
                                        choiceForAdmin = scan.nextInt();
                                    } catch (InputMismatchException e) {
                                        // Gérer une entrée non numérique
                                        System.out.println("Veuillez entrer un chiffre.");
                                        scan.nextLine(); // Nettoyer le tampon d'entrée
                                        choiceForAdmin = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                        continue;
                                    }
                                    switch (choiceForAdmin){
                                        case 1 -> {
                                            UserSQL.UpdateUserToAdmin(userId);
                                            MenuAdmin();
                                        }
                                        case 2 -> MenuAdmin();
                                        default -> System.out.println("Le choix n'est pas valide");
                                    }
                                } while (choiceForAdmin != 2);
                            } else {
                                System.out.println("L'utilisateur n'existe pas");
                                break;
                            }
                        } while (userId != 0);
                    }
                    case 2 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choice != 2);
        }
    }
    public static void GetPrograms(){
        Scanner scan = new Scanner(System.in);
        List<Program> programs = ProgramSQL.GetPrograms();
        if (programs.isEmpty()){
            System.out.println("Il n'existe aucun parcours");
            int choiceForCreateProgram;
            do {
                System.out.println("Souhaitez-vous créer un parcours ?");
                System.out.println("1. Oui ");
                System.out.println("2. Non ");
                try {
                    choiceForCreateProgram = scan.nextInt();
                } catch (InputMismatchException e) {
                    // Gérer une entrée non numérique
                    System.out.println("Veuillez entrer un chiffre.");
                    scan.nextLine(); // Nettoyer le tampon d'entrée
                    choiceForCreateProgram = 0; // Réinitialiser le choix pour éviter une boucle infinie
                    continue;
                }
                switch (choiceForCreateProgram){
                    case 1 -> {
                        CreateProgram(scan);
                    }
                    case 2 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choiceForCreateProgram != 2);
        } else {
            for (Program program: programs) {
                System.out.println("Id : " + program.getId());
                System.out.println("Nom : " + program.getName());
                System.out.println("Description: " + program.getDescription());
                List<Job> jobs = program.getJobs();
                System.out.println(jobs.isEmpty() ? "Pas de débouché professionnel" :
                        "Débouchés professionnels : " + Job.getJobNamesSeparatedByCommas(jobs));
                System.out.println("---------------------------------");
            }
            int choice;
            do {
                System.out.println("1. Sélectionnés un parcours");
                System.out.println("2. Créer un parcours ");
                System.out.println("3. Sortir ");
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
                    case 1 -> {
                        GetUsers();
                        System.out.println();
                    }
                    case 2 -> CreateProgram(scan);
                    case 3 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choice != 3);
        }
    }
    public static void GetModules(){
        Scanner scan = new Scanner(System.in);
    }
    public static void GetLessons(){
        Scanner scan = new Scanner(System.in);
    }

    public static void CreateProgram(Scanner scan){
        scan.nextLine();
        System.out.println("Renseignez un nom");
        String name = scan.nextLine();
        System.out.println("Renseignez une description");
        String description = scan.nextLine();
        List<Job> jobs = Job.GetAllJobs();
        int choiceJobId;
        List<Job> programJobs = new ArrayList<>();
        boolean jobHasAdded = false;
        do{
            for (Job job: jobs) {
                System.out.println(job.getId() + " : " + job.getFrenchLabel());
            }
            System.out.println((jobs.size() + 1) + " : Créer le parcours");
            if (!programJobs.isEmpty()){
                if (jobHasAdded){
                    System.out.println("Métier déjà ajouté");
                    jobHasAdded = false;
                } else {
                    System.out.println("Métier ajouté avec succés");
                }
            }
            System.out.println("Renseignez l'id du métier débouché ou le nombre pour créer le parcours");
            try {
                choiceJobId = scan.nextInt();
            } catch (InputMismatchException e) {
                // Gérer une entrée non numérique
                System.out.println("Veuillez entrer un chiffre.");
                scan.nextLine(); // Nettoyer le tampon d'entrée
                choiceJobId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                continue;
            }
            int finalChoiceJobId = choiceJobId;
            if (jobs.stream().anyMatch(job -> job.getId() == finalChoiceJobId)){
                if (!programJobs.contains(jobs.get(finalChoiceJobId-1))){
                    programJobs.add(jobs.get(finalChoiceJobId-1));
                } else {
                    jobHasAdded = true;
                }
            } else if (choiceJobId == (jobs.size()+1)) {
                break;
            }
            else {
                System.out.println("Le choix n'est pas valide");
            }
        } while (choiceJobId != (jobs.size() + 1));
        ProgramSQL.AddProgram(name, description, Program.setJobIds(programJobs));
        MenuAdmin();
    }
}