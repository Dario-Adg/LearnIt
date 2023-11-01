package dataBaseSQL;

import back.Module;
import back.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataBaseSQL.Helper.EncodeStringDateToDate;
import static dataBaseSQL.Helper.hashPassword;

public class ModuleSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

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
}
