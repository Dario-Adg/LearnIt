package org.example;

import back.*;
import back.Module;
import dataBaseSQL.LessonSQL;
import dataBaseSQL.ModuleSQL;
import dataBaseSQL.ProgramSQL;
import dataBaseSQL.UserSQL;

import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private static int UserId;

    public static void MenuUser(int userId){
        UserId = userId;
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
                case 3 -> {
                    System.out.println("A bientôt");
                    Main.MenuLoginInscription();
                }
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 3);
    }

    public static void GetUserPrograms(){
        List<UserProgram> userPrograms = ProgramSQL.GetProgramsByUserId(UserId);
        if (userPrograms.isEmpty()){
            System.out.println("Vous n'avez encore aucun parcours");
        } else{
            UserProgram userProgramInProgress = null;
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
                if (!programValid){
                    userProgramInProgress = userProgram;
                }
            }
            if (userProgramInProgress != null){
                Scanner scan = new Scanner(System.in);
                int choix;
                do {
                    System.out.println("1. Étudier le parcours (Parcours : " + userProgramInProgress.GetProgram().GetName() + ")");
                    System.out.println("2. Se désinscrire du parcours");
                    System.out.println("3. Quitter");
                    choix = scan.nextInt();

                    switch (choix) {
                        case 1 -> {
                            FollowUserProgram(userProgramInProgress);
                            choix = 3;
                        }
                        case 2 -> UserSQL.DeleteUserProgram(UserId, userProgramInProgress.GetProgram().GetId());
                        case 3 -> MenuUser(UserId);
                        default -> System.out.println("Le choix n'est pas valide");
                    }
                } while (choix != 3);
            }
        }
    }

    public static void AddUserProgram(){
        Scanner scan = new Scanner(System.in);
        List<UserProgram> userPrograms = ProgramSQL.GetProgramsByUserId(UserId);
        boolean programInProgress = false;
        for (UserProgram userProgram: userPrograms) {
            if (!userProgram.IsValid()){
                System.out.println("Vous êtes déjà inscrit a un parcours ("+ userProgram.GetProgram().GetName() +"), " +
                        "vous ne pouvez par avoir deux parcours en cours");
                programInProgress = true;
                break;
            }
        }
        if (programInProgress){
            return;
        }
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
            System.out.println("Indiquer l'Id du parcours auquel vous souhaitez vous inscrire");
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
                UserSQL.AddUserProgram(UserId, program.GetId());
            } else {
                System.out.println("Le parcours n'existe pas");
                break;
            }
        } while (programId == 0);
    }

    public static void FollowUserProgram(UserProgram userProgram){
        Program program = userProgram.GetProgram();
        System.out.println("--------------------------");
        System.out.println("Nom du parcours : " + program.GetName());
        System.out.println("--------------------------");
        List<Module> modules = program.GetModules();
        for (Module module : modules){
            System.out.println("- " + module.GetName());
            List<Lesson> lessons = module.GetLessons();
            for (Lesson lesson : lessons){
                System.out.println("    - " + lesson.GetName());
            }
            System.out.println("---");
        }
        System.out.println("--------------------------");
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("1. Continuer ce parcours ");
            System.out.println("2. Quitter");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> {
                    Module moduleInProgress = null;
                    Lesson lessonInProgress = null;
                    for (Module module : modules){
                        if(!ModuleSQL.ModuleIsValidInProgram(UserId, program.GetId(), module.GetId())){
                            moduleInProgress = module;
                            for (Lesson lesson : module.GetLessons()) {
                                if (!LessonSQL.LessonIsValidInProgram(UserId, program.GetId(), module.GetId(), lesson.GetId())) {
                                    lessonInProgress = lesson;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (moduleInProgress == null){
                        ProgramSQL.ValidProgram(UserId, program.GetId());
                        GetUserPrograms();
                        return;
                    }
                    if (lessonInProgress == null){
                        ModuleSQL.ValidModuleInProgram(UserId, program.GetId(), moduleInProgress.GetId());
                        for (Module moduleInProgram : program.GetModules()){
                            if(!ModuleSQL.ModuleIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId())){
                                moduleInProgress = moduleInProgram;
                                for (Lesson lessonInModule : moduleInProgram.GetLessons()) {
                                    if (!LessonSQL.LessonIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId(), lessonInModule.GetId())) {
                                        lessonInProgress = lessonInModule;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        Next(program, moduleInProgress, lessonInProgress);
                    }
                    Next(program, moduleInProgress, lessonInProgress);
                }
                case 2 -> GetUserPrograms();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 2);
    }

    public static void Next(Program program, Module module, Lesson lesson){
        System.out.println("Module : " + module.GetName());
        System.out.println("Description : " + module.GetDescription());
        System.out.println("--");
        System.out.println("Cours : " + lesson.GetName());
        System.out.println("Description : " + lesson.GetDescription());
        Scanner scan = new Scanner(System.in);
        int choix;
        do {
            System.out.println("1. Suivant ");
            System.out.println("2. Quitter");
            choix = scan.nextInt();

            switch (choix) {
                case 1 -> {
                    LessonSQL.ValidLessonInProgram(UserId, program.GetId(), module.GetId(), lesson.GetId());
                    Module moduleInProgress = null;
                    Lesson lessonInProgress = null;
                    for (Module moduleInProgram : program.GetModules()){
                        if(!ModuleSQL.ModuleIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId())){
                            moduleInProgress = moduleInProgram;
                            for (Lesson lessonInModule : moduleInProgram.GetLessons()) {
                                if (!LessonSQL.LessonIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId(), lessonInModule.GetId())) {
                                    lessonInProgress = lessonInModule;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (moduleInProgress == null){
                        ProgramSQL.ValidProgram(UserId, program.GetId());
                        GetUserPrograms();
                        return;
                    }
                    if (lessonInProgress == null){
                        ModuleSQL.ValidModuleInProgram(UserId, program.GetId(), module.GetId());
                        for (Module moduleInProgram : program.GetModules()){
                            if(!ModuleSQL.ModuleIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId())){
                                moduleInProgress = moduleInProgram;
                                for (Lesson lessonInModule : moduleInProgram.GetLessons()) {
                                    if (!LessonSQL.LessonIsValidInProgram(UserId, program.GetId(), moduleInProgram.GetId(), lessonInModule.GetId())) {
                                        lessonInProgress = lessonInModule;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        Next(program, moduleInProgress, lessonInProgress);
                    }
                    Next(program, moduleInProgress, lessonInProgress);
                }
                case 2 -> GetUserPrograms();
                default -> System.out.println("Le choix n'est pas valide");
            }
        } while (choix != 2);
    }
}