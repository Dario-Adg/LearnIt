package dataBaseSQL;

import back.User;
import back.UserProgram;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataBaseSQL.Helper.EncodeStringDateToDate;
import static dataBaseSQL.Helper.hashPassword;

public class UserSQL {
    static Connection connection = ConnectionBDD.ConnectionBDD();

    public static void AddUser(String email, String password, String firstName, String lastName, boolean isJobSeeker,
                               String dateOfBirth){
        String sql = "INSERT INTO user (`Email`, `Password`, `FirstName`, `LastName`, `IsAdmin`, `IsJobSeeker`, `DateOfBirth`) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            String hashedPassword = hashPassword(password);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setBoolean(5, false);
            preparedStatement.setBoolean(6, isJobSeeker);
            preparedStatement.setDate(7, new java.sql.Date(EncodeStringDateToDate(dateOfBirth).getTime()));
            preparedStatement.executeUpdate();
            System.out.println("Utilisateur crée avec succès");
        } catch (SQLException ex) {
            //Handle any errors
            if (ex.getErrorCode() == 1062){
                System.out.println("Adresse mail déjà utilisé veuillez vous connecter ou changer d'adresse");
            }else{
                System.out.println("SQLException : " +ex.getMessage());
                System.out.println("SQLState : " + ex.getSQLState());
                System.out.println("VendorError : " + ex.getErrorCode());
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static User AuthenticateUser(String emailInput, String password){
        String sql = "SELECT * FROM user WHERE Email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, emailInput);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("Password");
                    // Hasher le mot de passe entré lors de la connexion
                    String hashedPassword = hashPassword(password);
                    if (storedHashedPassword.equals(hashedPassword)){
                        int id = resultSet.getInt("Id");
                        String email = resultSet.getString("Email");
                        String firstName = resultSet.getString("FirstName");
                        String lastName = resultSet.getString("LastName");
                        boolean isAdmin = resultSet.getBoolean("IsAdmin");
                        boolean isJobSeeker = resultSet.getBoolean("IsJobSeeker");
                        int diplomaNumber = resultSet.getInt("DiplomaNumber");
                        Date dateOfBirth = resultSet.getDate("DateOfBirth");
                        return new User(id, firstName, lastName, email, storedHashedPassword, isAdmin, isJobSeeker,
                                diplomaNumber, dateOfBirth);
                    }
                }
                //Close
                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<User> GetUsers(){
        String sql = "SELECT Id, FirstName, LastName FROM user WHERE IsAdmin <> 1";
        List<User> users = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql);) {
            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                User user = new User(id, firstName, lastName);
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

    public static User GetUserByIdWithProgram(int userId){
        String sql = "SELECT * FROM user WHERE Id = ? AND IsAdmin <> 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String email = resultSet.getString("Email");
                    String password = resultSet.getString("Password");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    boolean isJobSeeker = resultSet.getBoolean("IsJobSeeker");
                    java.sql.Date dateOfBirth = resultSet.getDate("DateOfBirth");
                    int diplomaNumber = resultSet.getInt("DiplomaNumber");

                    User user = new User(id, firstName, lastName, email, password, isJobSeeker, diplomaNumber, dateOfBirth);

                    List<UserProgram> userPrograms = ProgramSQL.GetProgramsByUserId(id);
                    if (!userPrograms.isEmpty()){
                        for (UserProgram userProgram: userPrograms) {
                            user.addProgram(userProgram.getProgram(), userProgram.isValid(), userProgram.getEndDateProgram());
                        }
                    }
                    return user;
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

    public static void UpdateUserToAdmin(int userId){
        String sql = "UPDATE `user` SET `IsAdmin` = '1' WHERE `user`.`Id` = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mise à jour réussie : L'utilisateur est maintenant administrateur.");
            } else {
                System.out.println("Erreur lors du passage de cette utilisateur en admin");
            }
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
}
