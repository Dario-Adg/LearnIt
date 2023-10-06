package back;

import java.sql.*;

public class User {

    public String id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public boolean IsAdmin;
    public boolean IsJobSeeker;
    public String DateOfBirth;
    public int DiplomaNumber;
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public User (String firstName, String lastName, String email, String password, boolean isAdmin, boolean isJobSeeker,
                 String dateOfBirth, int diplomaNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.IsAdmin = isAdmin;
        this.IsJobSeeker = isJobSeeker;
        this.DateOfBirth = dateOfBirth;
        this.DiplomaNumber = diplomaNumber;

    }

    public void AddUser(){


        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "INSERT INTO user " +
                    "(Email, Password, FirstName, LastName, IsAdmin, IsJobSeeker, DateOfBirth, DiplomaNumber)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, Password);
            stmt.setString(3, FirstName);
            stmt.setString(4, LastName);
            stmt.setBoolean(5, IsAdmin);
            stmt.setBoolean(6, IsJobSeeker);
            stmt.setString(7, DateOfBirth);
            stmt.setInt(8, DiplomaNumber);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  void deleteUser(String _id) {
        int id = Integer.parseInt(_id);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "DELETE FROM user WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  void updateUser(String _id) {
        int id = Integer.parseInt(_id);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "UPDATE user SET Email = ?, Password = ?, FirstName = ?, LastName=?," +
                    " IsAdmin=?, IsJobSeeker=?, DateOfBirth=?, DiplomaNumber=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, Password);
            stmt.setString(3, FirstName);
            stmt.setString(4, LastName);
            stmt.setBoolean(5, IsAdmin);
            stmt.setBoolean(6, IsJobSeeker);
            stmt.setString(7, DateOfBirth);
            stmt.setInt(8, DiplomaNumber);
            stmt.setInt(9, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
