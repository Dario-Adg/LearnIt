package dataBaseSQL;

import back.Lesson;
import back.Module;
import back.Program;
import back.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramSelection {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static List<User> GetAllUsers(){
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                int id = rs.getInt("Id");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                boolean isAdmin = rs.getBoolean("IsAdmin");
                boolean isJobSeeker = rs.getBoolean("IsJobSeeker");
                java.sql.Date dateOfBirth = rs.getDate("DateOfBirth");

                int diplomaNumber = rs.getInt("DiplomaNumber");

                User user = new User(id, firstName, lastName, email, password, isAdmin,
                        isJobSeeker, diplomaNumber, dateOfBirth);

                users.add(user);
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return users;
    }

    public static List<Program> GetAllPrograms() {
        String sql = "SELECT * FROM program";
        List<Program> programs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                Program program = new Program(id, name, description);

                programs.add(program);
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return programs;
    }

    public static List<Module> GetAllModules() {
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

    public static List<Program> GetProgramsByUserId(int userId) {
        String sql = "SELECT program.* FROM program " +
                "INNER JOIN user_program ON program.id = user_program.ProgramId " +
                "WHERE user_program.UserId = ?";
        List<Program> programs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Program program = new Program(id, name, description);

                    programs.add(program);
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return programs;
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