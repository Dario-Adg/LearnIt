package dataBaseSQL;

import back.*;
import back.Module;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataBaseSQL.Helper.EncodeStringDateToDate;
import static dataBaseSQL.Helper.hashPassword;

public class ModuleSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static List<Module> GetModules() {
        String sql = "SELECT * FROM module";
        List<Module> modules = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                Module module = new Module(id, name, description);

                modules.add(module);
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return modules;
    }

    public static List<Module> GetModulesByProgramId(int programId) {
        String sql = "SELECT module.* FROM module " +
                "INNER JOIN program_module ON module.id = program_module.ModuleId " +
                "WHERE program_module.ProgramId = ?";
        List<Module> modules = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, programId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Module module = new Module(id, name, description);

                    modules.add(module);
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return modules;
    }

    public static void AddModule(String name, String description){
        String sql = "INSERT INTO module (`Name`, `Description`) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            System.out.println("Module créé avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static void UpdateModule(int moduleId, String name, String description){
        String sql = "UPDATE module SET `Name` = ?, `Description` = ? WHERE `Id` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, moduleId);
            preparedStatement.executeUpdate();
            System.out.println("Module modifié avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static void DeleteModule(int moduleId){
        String sql = "DELETE FROM module WHERE `Id` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            preparedStatement.executeUpdate();
            System.out.println("Module supprimé avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }

    public static Module GetModuleByIdForDisplay(int moduleId){
        String sql = "SELECT * FROM module WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Module module = new Module(id, name, description);
                    List<Lesson> lessons = LessonSQL.GetLessonsByModuleId(moduleId);
                    if (!lessons.isEmpty()){
                        for (Lesson lesson: lessons) {
                            module.AddLesson(lesson);
                        }
                    }
                    List<Program> programs = ProgramSQL.GetProgramsByModuleId(moduleId);
                    if (!programs.isEmpty()){
                        for (Program program: programs) {
                            module.AddProgram(program);
                        }
                    }
                    return module;
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return null;
    }

    public static void AddModuleLesson(int moduleId, int lessonId){
        String sql = "INSERT INTO module_lesson (`ModuleId`, `LessonId`) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            preparedStatement.setInt(2, lessonId);
            preparedStatement.executeUpdate();
            System.out.println("Cours ajouté au module avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static void DeleteModuleLesson(int moduleId, int lessonId){
        String sql = "DELETE FROM module_lesson " +
                "WHERE `module_lesson`.`ModuleId` = ? " +
                "AND `module_lesson`.`LessonId` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            preparedStatement.setInt(2, lessonId);
            preparedStatement.executeUpdate();
            System.out.println("Cours supprimé d'un module avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static List<Module> GetModulesWithThisLesson(int lessonId) {
        String sql = "SELECT module.* FROM module " +
                "INNER JOIN module_lesson ON module.id = module_lesson.ModuleId " +
                "WHERE module_lesson.LessonId = ?";
        List<Module> modules = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Module module = new Module(id, name, description);

                    modules.add(module);
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return modules;
    }

    public static boolean ModuleIsValidInProgram(int userId, int programId, int moduleId) {
        String sql = "SELECT * FROM note_module WHERE note_module.UserId = ? AND note_module.ProgramId = ? " +
                "AND note_module.ModuleId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, programId);
            preparedStatement.setInt(3, moduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("IsValid");
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return false;
    }

    public static void ValidModuleInProgram(int userId, int programId, int moduleId){
        String sql = "INSERT INTO note_module (`UserId`, `ProgramId`, `ModuleId`, `Note`, `IsValid`) VALUES (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, programId);
            preparedStatement.setInt(3, moduleId);
            preparedStatement.setInt(4, 0);
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate();
            System.out.println("****");
            System.out.println("**Module validé**");
            System.out.println("****");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
}
