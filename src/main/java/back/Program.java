package back;

import java.sql.*;
import java.util.List;

public class Program {
    public String Name;
    public String Description;
    public List<Job> Jobs;
    public List<ProgramModule> ProgramModules;
    public Connection conn = null;
    public PreparedStatement stmt = null;


    public Program (String name, String description, List<Job> jobs, List<ProgramModule> programModules){
        this.Name = name;
        this.Description = description;
        this.Jobs = jobs;
        this.ProgramModules = programModules;
    }

    public void isAdmin(){

    }

    public void AddModule() {

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

