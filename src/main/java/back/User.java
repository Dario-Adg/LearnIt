package back;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class User {
    public int Id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public boolean IsAdmin;
    public boolean IsJobSeeker;
    public int DiplomaNumber;
    public List<UserProgram> UserPrograms;

    public Date DateOfBirth;

    public User (String firstName, String lastName, String email, String password, boolean isAdmin,
                 boolean isJobSeeker, int DiplomaNumber, List<UserProgram> userPrograms, Date dateOfBirth){
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.IsAdmin = isAdmin;
        this.IsJobSeeker = isJobSeeker;
        this.DiplomaNumber = DiplomaNumber;
        this.UserPrograms = userPrograms;
        this.DateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public boolean getIsAdmin() {
        return this.IsAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.IsAdmin = isAdmin;
    }

    public boolean getIsJobSeeker() {
        return this.IsJobSeeker;
    }

    public void setIsJobSeeker(boolean isJobSeeker) {
        this.IsJobSeeker = isJobSeeker;
    }

    public Integer getDiplomaNumber() {
        return this.DiplomaNumber;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.DiplomaNumber = diplomaNumber;
    }

    public List<UserProgram> getUserPrograms() {
        return this.UserPrograms;
    }

    public void setUserPrograms(List<UserProgram> userPrograms) {
        this.UserPrograms = userPrograms;
    }

    public Date getDateOfBirth() {
        return this.DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public static ArrayList<HashMap> allUsers(){

        Connection Conn = ConnectionBDD.ConnectionBDD();

        Statement Stmt = null;

        ResultSet Rs = null;

        ArrayList<HashMap> users = new ArrayList<>();

        /*
        DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
        Date Date = null;

        try {
            Date = Df.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        */

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String SqlQuery = "SELECT * FROM user";
            Stmt = Conn.createStatement();
            Rs = Stmt.executeQuery(SqlQuery);

            //Nom des Index dans la BDD : `Email`, `Password`, `FirstName`, `LastName`, `IsAdmin`, `IsJobSeeker`, `DateOfBirth`, `DiplomaNumber`
            while(Rs.next()){
                Integer id = Rs.getInt("Id");
                String email = Rs.getString("Email");
                String password = Rs.getString("Password");
                String firstName = Rs.getString("FirstName");
                String lastName = Rs.getString("LastName");
                boolean isAdmin = Rs.getBoolean("IsAdmin");
                boolean isJobSeeker = Rs.getBoolean("IsJobSeeker");
                java.sql.Date dateOfBirth = Rs.getDate("DateOfBirth");

                //Convertir java.sql.Date en java.util.Date

                Integer diplomaNumber = Rs.getInt("DiplomaNumber");

                HashMap user = new HashMap();
                user.put("Id", id);
                user.put("Email", email);
                user.put("Password", password);
                user.put("FirstName", firstName);
                user.put("LastName", lastName);
                user.put("IsAdmin", isAdmin);
                user.put("IsJobSeeker", isJobSeeker);
                user.put("DateOfBirth", dateOfBirth);


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

    public static void AddUser(String email, String password, String firstName, String lastName, boolean isJobSeeker,
                               String dateOfBirth){

        Connection Conn = ConnectionBDD.ConnectionBDD();

        PreparedStatement Stmt = null;

        DateFormat Df = new SimpleDateFormat("ddMMyyyy");
        Date Date = null;

        try {
            Date = Df.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String SqlQuery = "INSERT INTO user (`Email`, `Password`, `FirstName`, `LastName`, `IsAdmin`, `IsJobSeeker`, `DateOfBirth`) VALUES (?,?,?,?,?,?,?)";

            Stmt = Conn.prepareStatement(SqlQuery);
            Stmt.setString(1, email);
            Stmt.setString(2, password);
            Stmt.setString(3, firstName);
            Stmt.setString(4, lastName);
            Stmt.setBoolean(5, false);
            Stmt.setBoolean(6, isJobSeeker);
            Stmt.setDate(7, new java.sql.Date(Date.getTime()));

            Stmt.executeUpdate();

            // Fermeture du Statement
            if(Stmt != null) {
                Stmt.close();
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

    }

    public static void UpdateUserAsAdmin(String email, String password, String firstName, String lastName, boolean isAdmin,
                                         boolean isJobSeeker, String dateOfBirth, Integer diplomaNumber, Integer id){

        Connection Conn = ConnectionBDD.ConnectionBDD();

        PreparedStatement Stmt = null;

        DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
        Date Date = null;

        try {
            Date = Df.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String SqlQuery = "UPDATE user SET `Email` = ?, `Password` = ?, `FirstName` = ?, `LastName` = ?, `IsAdmin` = ?, `IsJobSeeker` = ?, `DateOfBirth` = ?, `DiplomaNumber` = ? WHERE `id` = ?";

            Stmt = Conn.prepareStatement(SqlQuery);
            Stmt.setString(1, email);
            Stmt.setString(2, password);
            Stmt.setString(3, firstName);
            Stmt.setString(4, lastName);
            Stmt.setBoolean(5, isAdmin);
            Stmt.setBoolean(6, isJobSeeker);
            Stmt.setDate(7, new java.sql.Date(Date.getTime()));
            Stmt.setInt(8, diplomaNumber);
            Stmt.setInt(9, id);

            Stmt.executeUpdate();

            // Fermeture du Statement
            if(Stmt != null) {
                Stmt.close();
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

    }

    public static void UpdateUserAsUser(String email, String password, String firstName, String lastName, String dateOfBirth, Integer diplomaNumber, Integer id){

        Connection Conn = ConnectionBDD.ConnectionBDD();

        PreparedStatement Stmt = null;

        DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
        Date Date = null;

        try {
            Date = Df.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String SqlQuery = "UPDATE user SET `Email` = ?, `Password` = ?, `FirstName` = ?, `LastName` = ?, `IsAdmin` = ?, `IsJobSeeker` = ?, `DateOfBirth` = ?, `DiplomaNumber` = ? WHERE `id` = ?";

            Stmt = Conn.prepareStatement(SqlQuery);
            Stmt.setString(1, email);
            Stmt.setString(2, password);
            Stmt.setString(3, firstName);
            Stmt.setString(4, lastName);
            Stmt.setDate(5, new java.sql.Date(Date.getTime()));
            Stmt.setInt(6, diplomaNumber);
            Stmt.setInt(7, id);

            Stmt.executeUpdate();

            // Fermeture du Statement
            if(Stmt != null) {
                Stmt.close();
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

    }

    public static void DeleteUser(Integer id){

        Connection Conn = ConnectionBDD.ConnectionBDD();

        PreparedStatement Stmt = null;

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String SqlQuery = "DELETE FROM user WHERE Id=?";

            Stmt = Conn.prepareStatement(SqlQuery);
            Stmt.setInt(1, id);

            Stmt.executeUpdate();

            // Fermeture du Statement
            if(Stmt != null) {
                Stmt.close();
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

    }

}
