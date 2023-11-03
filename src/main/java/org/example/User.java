package org.example;

import back.Job;
import back.Module;
import back.Program;
import back.UserProgram;
import dataBaseSQL.ProgramSQL;
import dataBaseSQL.UserSQL;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User {
    public static void MenuUser(int userId){
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
                case 1 -> GetUserPrograms(userId);
                case 2 -> AddUserProgram();
                case 3 -> System.out.println("A bientôt");
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 3);
    }
    public static void GetUserPrograms(int userId){
        Scanner scan = new Scanner(System.in);
        List<UserProgram> userPrograms = ProgramSQL.GetProgramsByUserId(userId);

        for (UserProgram userProgram: userPrograms) {
            System.out.println("**");
            System.out.println("Id du parcours : " + userProgram.getProgram().getId());
            System.out.println("Nom du parcours : " + userProgram.getProgram().getName());
            System.out.println("Description du parcours : " +
                    userProgram.getProgram().getDescription());
            boolean programValid = userProgram.isValid();
            System.out.println(programValid ? "Parcours validé" : "Parcours en cours");
            System.out.println(programValid ? "Parcours terminé" :
                    "Fin limite du parcours : " +
                            new SimpleDateFormat("dd MMMM yyyy").format(userProgram.getEndDateProgram()));
            System.out.println("**");
        }

    }
    public static void AddUserProgram(){

        Scanner scan = new Scanner(System.in);

        List<Program> programs = ProgramSQL.GetPrograms();

        for (Program program: programs) {
            System.out.println("Id : " + program.getId());
            System.out.println("Nom : " + program.getName());
            System.out.println("Description: " + program.getDescription());
            List<Job> jobs = program.getJobs();
            System.out.println(jobs.isEmpty() ? "Pas de débouché professionnel" :
                    "Débouchés professionnels : " + Job.getJobNamesSeparatedByCommas(jobs));
            System.out.println("--------------------------");
        }

        int programId;

        do{
            System.out.println("Indiquer l'Id du program auquel vous souhaitez vous inscrire");
            try {
                programId = scan.nextInt();
            } catch (InputMismatchException e) {
                // Gérer une entrée non numérique
                System.out.println("Veuillez entrer un chiffre.");
                scan.nextLine(); // Nettoyer le tampon d'entrée
                programId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                continue;
            }
            Program program = ProgramSQL.GetProgramByIdForDisplay(programId);
            if (program != null){
                System.out.println("--------------------------");
                System.out.println("Id : " + program.getId());
                System.out.println("Nom : " + program.getName());
                System.out.println("Description: " + program.getDescription());
                List<Job> jobs = program.getJobs();
                System.out.println(jobs.isEmpty() ? "Pas de débouché professionnel" :
                        "Débouchés professionnels : " + Job.getJobNamesSeparatedByCommas(jobs));
                List<Module> modules = program.getModules();

                if (!modules.isEmpty()){
                    for (Module module: modules) {
                        System.out.println("**");
                        System.out.println("Id du module : " + module.getId());
                        System.out.println("Nom du module : " + module.getName());
                        System.out.println("Description du module : " + module.getDescription());
                        System.out.println("**");
                    }
                } else {
                    System.out.println("Aucun module dans ce parcours");
                }
                System.out.println("--------------------------");
            } else {
                System.out.println("Le parcours n'existe pas");
                break;
            }

        } while (programId == 0);
    }
}