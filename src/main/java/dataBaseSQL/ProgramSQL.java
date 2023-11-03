package dataBaseSQL;

import back.Module;
import back.Program;
import back.User;
import back.UserProgram;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dataBaseSQL.Helper.EncodeStringDateToDate;
import static dataBaseSQL.Helper.hashPassword;

public class ProgramSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static List<Program> GetPrograms() {
        String sql = "SELECT * FROM program";
        List<Program> programs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                String jobIds = resultSet.getString("JobIds");
                Program program = new Program(id, name, description, jobIds);

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
    public static List<Program> GetProgramsWithThisModule(int moduleId) {
        String sql = "SELECT program.* FROM program " +
                "INNER JOIN program_module ON program.id = program_module.ProgramId " +
                "WHERE program_module.ModuleId = ?";
        List<Program> programs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, moduleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String jobIds = resultSet.getString("JobIds");
                    Program program = new Program(id, name, description, jobIds);

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

    public static List<UserProgram> GetProgramsByUserId(int userId) {
        String sql = "SELECT * FROM program " +
                "INNER JOIN user_program ON program.id = user_program.ProgramId " +
                "WHERE user_program.UserId = ?";
        List<UserProgram> userPrograms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String jobIds = resultSet.getString("JobIds");
                    Program program = new Program(id, name, description, jobIds);
                    boolean isValid = resultSet.getBoolean("IsValid");
                    Date endDateProgram = resultSet.getDate("EndDateProgram");
                    UserProgram userProgram = new UserProgram(null, program, isValid, endDateProgram);

                    userPrograms.add(userProgram);
                }
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        return userPrograms;
    }

    public static void AddProgram(String name, String description, String jobIds){
        String sql = "INSERT INTO program (`Name`, `Description`, `JobIds`) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, jobIds);
            preparedStatement.executeUpdate();
            System.out.println("Parcours créé avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }

    public static Program GetProgramByIdForDisplay(int programId){
        String sql = "SELECT * FROM program WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, programId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    String jobIds = resultSet.getString("JobIds");
                    Program program = new Program(id, name, description, jobIds);
                    List<Module> modules = ModuleSQL.GetModulesByProgramId(programId);
                    if (!modules.isEmpty()){
                        for (Module module: modules) {
                            program.addModule(module);
                        }
                    }
                    return program;
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

    public static void AddProgramModule(int programId, int moduleId){
        String sql = "INSERT INTO program_module (`ProgramId`, `ModuleId`) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, programId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
            System.out.println("Module ajouté au program avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
    public static void DeleteProgramModule(int programId, int moduleId){
        String sql = "DELETE FROM program_module " +
                "WHERE `program_module`.`ProgramId` = ? " +
                "AND `program_module`.`ModuleId` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, programId);
            preparedStatement.setInt(2, moduleId);
            preparedStatement.executeUpdate();
            System.out.println("Module supprimé du program avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
}
