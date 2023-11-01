package dataBaseSQL;

import back.Lesson;
import back.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataBaseSQL.Helper.EncodeStringDateToDate;
import static dataBaseSQL.Helper.hashPassword;

public class LessonSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static List<Lesson> GetAllLessons() {
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
}
