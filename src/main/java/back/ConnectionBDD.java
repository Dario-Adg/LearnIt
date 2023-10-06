package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionBDD {

    public static Connection ConnectionBDD(){

        Connection Conn = null;

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return Conn;

    }

}
