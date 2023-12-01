package dataBaseSQL;

import back.Lesson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static List<Lesson> GetLessons() {
        String sql = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                Lesson lesson = new Lesson(id, name, description);

                lessons.add(lesson);
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return lessons;
    }

    public static List<Lesson> GetLessonsByModuleId(int moduleId) {
        String sql = "SELECT lesson.* FROM lesson " +
                "INNER JOIN module_lesson ON lesson.id = module_lesson.LessonId " +
                "WHERE module_lesson.ModuleId = ?";
        List<Lesson> lessons = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Lesson lesson = new Lesson(id, name, description);

                    lessons.add(lesson);
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return lessons;
    }

    public static Lesson GetLessonByIdForDisplay(int lessonId){
        String sql = "SELECT * FROM lesson WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    return new Lesson(id, name, description);
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

    public static void AddLesson(String name, String description){
        String sql = "INSERT INTO lesson (`Name`, `Description`) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            System.out.println("Cours créé avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }

    public static void UpdateLesson(int lessonId, String name, String description){
        String sql = "UPDATE lesson SET `Name` = ?, `Description` = ? WHERE `Id` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, lessonId);
            preparedStatement.executeUpdate();
            System.out.println("Cours modifié avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static void DeleteLesson(int lessonId){
        String sql = "DELETE FROM lesson WHERE `Id` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            preparedStatement.executeUpdate();
            System.out.println("Cours supprimé avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }

    public static boolean LessonIsValidInProgram(int userId, int programId, int moduleId, int lessonId) {
        String sql = "SELECT EXISTS (SELECT 1 FROM note_lesson WHERE note_lesson.UserId = ? AND note_lesson.ProgramId = ? " +
                "AND note_lesson.ModuleId = ? AND note_lesson.LessonId = ?)";
        boolean moduleValid = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, programId);
            preparedStatement.setInt(3, moduleId);
            preparedStatement.setInt(4, lessonId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                moduleValid = resultSet.next();
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return moduleValid;
    }

    public static void ValidLessonInProgram(int userId, int programId, int moduleId, int lessonId){
        String sql = "INSERT INTO note_lesson (`UserId`, `ProgramId`, `ModuleId`, `LessonId`, `IsValid`) VALUES (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, programId);
            preparedStatement.setInt(3, moduleId);
            preparedStatement.setInt(4, lessonId);
            preparedStatement.setBoolean(5, true);
            preparedStatement.executeUpdate();
            System.out.println("****");
            System.out.println("**Cours validé**");
            System.out.println("****");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
}
