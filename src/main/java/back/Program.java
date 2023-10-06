package back;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public String Name;
    public List<Module> Modules;
    public String Description;
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public Program(String id, String name, List<Module> modules, String description){

        this.Name = name;
        this.Modules = modules;
        this.Description = description;

    }

    public void isAdmin(){

    }

    // Add for Program DataBase
    public void AddProgram() {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "INSERT INTO program (Name, Description) VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, Description);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delateProgram(int id) {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "DELETE FROM program WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void AddModule() {

        for (Module i: Modules) {
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
                    String sql = "INSERT INTO module (Name, Description) VALUES(?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, Name);
                    stmt.setString(2, Description);
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public void delateModule(int id) {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "DELETE FROM module WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
