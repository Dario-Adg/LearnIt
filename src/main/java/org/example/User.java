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
                case 1 -> GetUserPrograms(0);
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
            System.out.println("Id du parcours : " + userProgram.GetProgram().GetId());
            System.out.println("Nom du parcours : " + userProgram.GetProgram().GetName());
            System.out.println("Description du parcours : " +
                    userProgram.GetProgram().GetDescription());
            boolean programValid = userProgram.IsValid();
            System.out.println(programValid ? "Parcours validé" : "Parcours en cours");
            System.out.println(programValid ? "Parcours terminé" :
                    "Fin limite du parcours : " +
                            new SimpleDateFormat("dd MMMM yyyy").format(userProgram.GetEndDateProgram()));
            System.out.println("**");
        }

    }
    public static void AddUserProgram(){

        Scanner scan = new Scanner(System.in);

        List<Program> programs = ProgramSQL.GetPrograms();

        for (Program program: programs) {
            System.out.println("Id : " + program.GetId());
            System.out.println("Nom : " + program.GetName());
            System.out.println("Description: " + program.GetDescription());
            List<Job> jobs = program.GetJobs();
            System.out.println(jobs.isEmpty() ? "Pas de débouché professionnel" :
                    "Débouchés professionnels : " + Job.GetJobNamesSeparatedByCommas(jobs));
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
                System.out.println("Id : " + program.GetId());
                System.out.println("Nom : " + program.GetName());
                System.out.println("Description: " + program.GetDescription());
                List<Job> jobs = program.GetJobs();
                System.out.println(jobs.isEmpty() ? "Pas de débouché professionnel" :
                        "Débouchés professionnels : " + Job.GetJobNamesSeparatedByCommas(jobs));
                List<Module> modules = program.GetModules();

                if (!modules.isEmpty()){
                    for (Module module: modules) {
                        System.out.println("**");
                        System.out.println("Id du module : " + module.GetId());
                        System.out.println("Nom du module : " + module.GetName());
                        System.out.println("Description du module : " + module.GetDescription());
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