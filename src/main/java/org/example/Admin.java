package org.example;

import back.*;
import back.Module;
import back.User;
import dataBaseSQL.LessonSQL;
import dataBaseSQL.ModuleSQL;
import dataBaseSQL.ProgramSQL;
import dataBaseSQL.UserSQL;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.example.Main.MenuLoginInscription;

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

    private static void GetUsers(){
        List<User> users = UserSQL.GetUsers();
        if (users.isEmpty()){
            System.out.println("Aucun utilisateur");
        } else {
            for (User user: users) {
                System.out.println("Id : " + user.getId());
                System.out.println("Prénom : " + user.getFirstName());
                System.out.println("Nom : " + user.getLastName());
                System.out.println("--------------------------");
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
                            User user = UserSQL.GetUserByIdForDisplay(userId);
                            if (user != null){
                                System.out.println("Id : " + user.getId());
                                System.out.println("Prénom : " + user.getFirstName());
                                System.out.println("Nom : " + user.getLastName());
                                System.out.println("Date de naissance : " +
                                        new SimpleDateFormat("dd MMMM yyyy").format(user.getDateOfBirth()));
                                System.out.println("Chercheur d'emplois : " + (user.getIsJobSeeker() ? "Oui" : "Non"));
                                System.out.println(user.getDiplomaNumber() != 0 ?
                                        "Numéro diplôme : " + user.getDiplomaNumber() : "Pas encore de numéro de diplôme");
                                List<UserProgram> userPrograms = user.getUserPrograms();
                                if (!userPrograms.isEmpty()){
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
                                } else {
                                    System.out.println("Aucun parcours suivi par " + user.getFirstNameLastName());
                                }
                                System.out.println("--------------------------");
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

    private static void GetPrograms(){
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
                    case 1 -> CreateProgram(scan);
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
                System.out.println("--------------------------");
            }
            int choice;
            do {
                System.out.println("1. Sélectionner un parcours");
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
                        int programId;
                        do{
                            System.out.println("Indiquer l'Id du program souhaité");
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
                    case 2 -> CreateProgram(scan);
                    case 3 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choice != 3);
        }
    }

    private static void GetModules(){
        Scanner scan = new Scanner(System.in);
        List<Module> modules = ModuleSQL.GetModules();
        if (modules.isEmpty()){
            System.out.println("Il n'existe aucun module");
            int choiceForCreateModule;
            do {
                System.out.println("Souhaitez-vous créer un module ?");
                System.out.println("1. Oui ");
                System.out.println("2. Non ");
                try {
                    choiceForCreateModule = scan.nextInt();
                } catch (InputMismatchException e) {
                    // Gérer une entrée non numérique
                    System.out.println("Veuillez entrer un chiffre.");
                    scan.nextLine(); // Nettoyer le tampon d'entrée
                    choiceForCreateModule = 0; // Réinitialiser le choix pour éviter une boucle infinie
                    continue;
                }
                switch (choiceForCreateModule){
                    case 1 -> CreateModule(scan);
                    case 2 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choiceForCreateModule != 2);
        } else {
            for (Module module: modules) {
                System.out.println("Id : " + module.getId());
                System.out.println("Nom : " + module.getName());
                System.out.println("Description: " + module.getDescription());
                System.out.println("--------------------------");
            }
            int choice;
            do {
                System.out.println("1. Sélectionner un module");
                System.out.println("2. Créer un module ");
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
                        int moduleId;
                        do{
                            System.out.println("Indiquer l'Id du module souhaité");
                            try {
                                moduleId = scan.nextInt();
                            } catch (InputMismatchException e) {
                                // Gérer une entrée non numérique
                                System.out.println("Veuillez entrer un chiffre.");
                                scan.nextLine(); // Nettoyer le tampon d'entrée
                                moduleId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                continue;
                            }
                            Module module = ModuleSQL.GetModuleByIdForDisplay(moduleId);
                            if (module != null){
                                System.out.println("--------------------------");
                                System.out.println("Id : " + module.getId());
                                System.out.println("Nom : " + module.getName());
                                System.out.println("Description: " + module.getDescription());
                                List<Lesson> lessons = module.getLessons();

                                if (!lessons.isEmpty()){
                                    for (Lesson lesson: lessons) {
                                        System.out.println("**");
                                        System.out.println("Id du cours : " + lesson.getId());
                                        System.out.println("Nom du cours : " + lesson.getName());
                                        System.out.println("Description du cours : " + lesson.getDescription());
                                        System.out.println("**");
                                    }
                                } else {
                                    System.out.println("Aucun cours dans ce module");
                                }
                                System.out.println("--------------------------");
                                int choiceForSelectedModule;
                                do {
                                    System.out.println("1. Attribuer ce module à un parcours");
                                    System.out.println("2. Supprimer ce module d'un parcours");
                                    System.out.println("3. Modifier ce module");
                                    System.out.println("4. Supprimer ce module");
                                    System.out.println("5. Revenir au menu admin");
                                    System.out.println("--------------------------");
                                    try {
                                        choiceForSelectedModule = scan.nextInt();
                                    } catch (InputMismatchException e) {
                                        // Gérer une entrée non numérique
                                        System.out.println("Veuillez entrer un chiffre.");
                                        scan.nextLine(); // Nettoyer le tampon d'entrée
                                        choiceForSelectedModule = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                        continue;
                                    }
                                    switch (choiceForSelectedModule){
                                        case 1 -> {
                                            List<Program> programs = ProgramSQL.GetPrograms();
                                            for (Program program: programs) {
                                                System.out.println("Id : " + program.getId());
                                                System.out.println("Nom : " + program.getName());
                                                System.out.println("Description: " + program.getDescription());
                                                System.out.println("--------------------------");
                                            }
                                            int programId;
                                            do{
                                                System.out.println("Indiquer l'Id du program ou vous souhaitez ajouté ce module");
                                                try {
                                                    programId = scan.nextInt();
                                                } catch (InputMismatchException e) {
                                                    // Gérer une entrée non numérique
                                                    System.out.println("Veuillez entrer un chiffre.");
                                                    scan.nextLine(); // Nettoyer le tampon d'entrée
                                                    programId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                                    continue;
                                                }
                                                int finalProgramId = programId;
                                                if (programs.stream().anyMatch(program -> program.getId() == finalProgramId)){
                                                    ProgramSQL.AddProgramModule(finalProgramId, module.getId());
                                                }
                                                else{
                                                    System.out.println("Indiquer un l'id d'un program valide");
                                                }
                                            } while (programId == 0);
                                        }
                                        case 2 -> {
                                            List<Program> programs = ProgramSQL.GetProgramsWithThisModule(module.getId());
                                            if (programs.isEmpty()){
                                                System.out.println("Aucun program ne possède ce parcours");
                                            } else {
                                                for (Program program: programs) {
                                                    System.out.println("Id : " + program.getId());
                                                    System.out.println("Nom : " + program.getName());
                                                    System.out.println("Description: " + program.getDescription());
                                                    System.out.println("--------------------------");
                                                }
                                                int programId;
                                                do{
                                                    System.out.println("Indiquer l'Id du program ou vous souhaitez ajouté ce module");
                                                    try {
                                                        programId = scan.nextInt();
                                                    } catch (InputMismatchException e) {
                                                        // Gérer une entrée non numérique
                                                        System.out.println("Veuillez entrer un chiffre.");
                                                        scan.nextLine(); // Nettoyer le tampon d'entrée
                                                        programId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                                        continue;
                                                    }
                                                    int finalProgramId = programId;
                                                    if (programs.stream().anyMatch(program -> program.getId() == finalProgramId)){
                                                        ProgramSQL.DeleteProgramModule(finalProgramId, module.getId());
                                                    }
                                                    else{
                                                        System.out.println("Indiquer un l'id d'un program valide");
                                                    }
                                                } while (programId == 0);
                                            }
                                        }
                                        case 3 -> {
                                            scan.nextLine();
                                            System.out.println("Indiquer le nouveau nom, " +
                                                    "laissé vide si vous souhaitez ne pas changé");
                                            String newName = scan.nextLine();
                                            System.out.println("Indiquer la nouvelle description, " +
                                                    "laissé vide si vous souhaitez ne pas changé");
                                            String newDescription = scan.nextLine();
                                            if (newName.isBlank()){
                                                newName = module.getName();
                                            }
                                            if (newDescription.isBlank()){
                                                newDescription = module.getDescription();
                                            }
                                            ModuleSQL.UpdateModule(module.getId(), newName, newDescription);
                                        }
                                        case 4 -> {
                                            ModuleSQL.DeleteModule(module.getId());
                                        }
                                        case 5 -> MenuAdmin();
                                        default -> System.out.println("Le choix n'est pas valide");
                                    }
                                } while (choiceForSelectedModule != 5);
                            } else {
                                System.out.println("Le module n'existe pas");
                                break;
                            }
                        } while (moduleId == 0);
                    }
                    case 2 -> CreateModule(scan);
                    case 3 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choice != 3);
        }
    }

    private static void GetLessons(){
        Scanner scan = new Scanner(System.in);
        List<Lesson> lessons = LessonSQL.GetLessons();
        if (lessons.isEmpty()){
            System.out.println("Il n'existe aucun cours");
            int choiceForCreateLesson;
            do {
                System.out.println("Souhaitez-vous créer un cours ?");
                System.out.println("1. Oui ");
                System.out.println("2. Non ");
                try {
                    choiceForCreateLesson = scan.nextInt();
                } catch (InputMismatchException e) {
                    // Gérer une entrée non numérique
                    System.out.println("Veuillez entrer un chiffre.");
                    scan.nextLine(); // Nettoyer le tampon d'entrée
                    choiceForCreateLesson = 0; // Réinitialiser le choix pour éviter une boucle infinie
                    continue;
                }
                switch (choiceForCreateLesson){
                    case 1 -> CreateLesson(scan);
                    case 2 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choiceForCreateLesson != 2);
        } else {
            for (Lesson lesson: lessons) {
                System.out.println("Id : " + lesson.getId());
                System.out.println("Nom : " + lesson.getName());
                System.out.println("Description: " + lesson.getDescription());
                System.out.println("--------------------------");
            }
            int choice;
            do {
                System.out.println("1. Sélectionner un cours");
                System.out.println("2. Créer un cours ");
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
                        int lessonId;
                        do{
                            System.out.println("Indiquer l'Id du cours souhaité");
                            try {
                                lessonId = scan.nextInt();
                            } catch (InputMismatchException e) {
                                // Gérer une entrée non numérique
                                System.out.println("Veuillez entrer un chiffre.");
                                scan.nextLine(); // Nettoyer le tampon d'entrée
                                lessonId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                continue;
                            }
                            Lesson lesson = LessonSQL.GetLessonByIdForDisplay(lessonId);
                            if (lesson != null){
                                System.out.println("--------------------------");
                                System.out.println("Id : " + lesson.getId());
                                System.out.println("Nom : " + lesson.getName());
                                System.out.println("Description: " + lesson.getDescription());
                                System.out.println("--------------------------");
                                int choiceForSelectedLesson;
                                do {
                                    System.out.println("1. Attribuer ce cours à un module");
                                    System.out.println("2. Supprimer ce cours d'un module");
                                    System.out.println("3. Modifier ce cours");
                                    System.out.println("4. Supprimer ce cours");
                                    System.out.println("5. Revenir au menu admin");
                                    System.out.println("--------------------------");
                                    try {
                                        choiceForSelectedLesson = scan.nextInt();
                                    } catch (InputMismatchException e) {
                                        // Gérer une entrée non numérique
                                        System.out.println("Veuillez entrer un chiffre.");
                                        scan.nextLine(); // Nettoyer le tampon d'entrée
                                        choiceForSelectedLesson = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                        continue;
                                    }
                                    switch (choiceForSelectedLesson){
                                        case 1 -> {
                                            List<Module> modules = ModuleSQL.GetModules();
                                            for (Module module: modules) {
                                                System.out.println("Id : " + module.getId());
                                                System.out.println("Nom : " + module.getName());
                                                System.out.println("Description: " + module.getDescription());
                                                System.out.println("--------------------------");
                                            }
                                            int moduleId;
                                            do{
                                                System.out.println("Indiquer l'Id du module ou vous souhaitez ajouté ce cours");
                                                try {
                                                    moduleId = scan.nextInt();
                                                } catch (InputMismatchException e) {
                                                    // Gérer une entrée non numérique
                                                    System.out.println("Veuillez entrer un chiffre.");
                                                    scan.nextLine(); // Nettoyer le tampon d'entrée
                                                    moduleId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                                    continue;
                                                }
                                                int finalModuleId = moduleId;
                                                if (modules.stream().anyMatch(module -> module.getId() == finalModuleId)){
                                                    ModuleSQL.AddModuleLesson(finalModuleId, lesson.getId());
                                                }
                                                else{
                                                    System.out.println("Indiquer un l'id d'un module valide");
                                                }
                                            } while (moduleId == 0);
                                        }
                                        case 2 -> {
                                            List<Module> modules = ModuleSQL.GetModulesWithThisLesson(lesson.getId());
                                            if (modules.isEmpty()){
                                                System.out.println("Aucun module ne possède ce parcours");
                                            } else {
                                                for (Module module: modules) {
                                                    System.out.println("Id : " + module.getId());
                                                    System.out.println("Nom : " + module.getName());
                                                    System.out.println("Description: " + module.getDescription());
                                                    System.out.println("--------------------------");
                                                }
                                                int moduleId;
                                                do{
                                                    System.out.println("Indiquer l'Id du module ou vous souhaitez ajouté ce cours");
                                                    try {
                                                        moduleId = scan.nextInt();
                                                    } catch (InputMismatchException e) {
                                                        // Gérer une entrée non numérique
                                                        System.out.println("Veuillez entrer un chiffre.");
                                                        scan.nextLine(); // Nettoyer le tampon d'entrée
                                                        moduleId = 0; // Réinitialiser le choix pour éviter une boucle infinie
                                                        continue;
                                                    }
                                                    int finalModuleId = moduleId;
                                                    if (modules.stream().anyMatch(module -> module.getId() == finalModuleId)){
                                                        ModuleSQL.DeleteModuleLesson(finalModuleId, lesson.getId());
                                                    }
                                                    else{
                                                        System.out.println("Indiquer un l'id d'un module valide");
                                                    }
                                                } while (moduleId == 0);
                                            }
                                        }
                                        case 3 -> {
                                            scan.nextLine();
                                            System.out.println("Indiquer le nouveau nom, " +
                                                    "laissé vide si vous souhaitez ne pas changé");
                                            String newName = scan.nextLine();
                                            System.out.println("Indiquer la nouvelle description, " +
                                                    "laissé vide si vous souhaitez ne pas changé");
                                            String newDescription = scan.nextLine();
                                            if (newName.isBlank()){
                                                newName = lesson.getName();
                                            }
                                            if (newDescription.isBlank()){
                                                newDescription = lesson.getDescription();
                                            }
                                            LessonSQL.UpdateLesson(lesson.getId(), newName, newDescription);
                                        }
                                        case 4 -> {
                                            LessonSQL.DeleteLesson(lesson.getId());
                                        }
                                        case 5 -> MenuAdmin();
                                        default -> System.out.println("Le choix n'est pas valide");
                                    }
                                } while (choiceForSelectedLesson != 5);
                            } else {
                                System.out.println("Le cours n'existe pas");
                                break;
                            }
                        } while (lessonId == 0);
                    }
                    case 2 -> CreateLesson(scan);
                    case 3 -> MenuAdmin();
                    default -> System.out.println("Le choix n'est pas valide");
                }
            } while (choice != 3);
        }
    }

    private static void CreateProgram(Scanner scan){
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
    private static void CreateModule(Scanner scan){
        scan.nextLine();
        System.out.println("Renseignez un nom");
        String name = scan.nextLine();
        System.out.println("Renseignez une description");
        String description = scan.nextLine();
        ModuleSQL.AddModule(name, description);
        MenuAdmin();
    }
    private static void CreateLesson(Scanner scan){
        scan.nextLine();
        System.out.println("Renseignez un nom");
        String name = scan.nextLine();
        System.out.println("Renseignez une description");
        String description = scan.nextLine();
        LessonSQL.AddLesson(name, description);
        MenuAdmin();
    }
}