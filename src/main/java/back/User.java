package back;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class User {
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

    public static void AddUser(String email, String password, String firstName, String lastName, boolean isJobSeeker,
                               String dateOfBirth){

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
}
