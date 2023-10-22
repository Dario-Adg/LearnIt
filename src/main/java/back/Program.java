package back;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {
    public String Name;
    public String Description;
    public List<Job> Jobs;
    public List<ProgramModule> ProgramModules;
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public ResultSet rs = null;



    public Program (String name, String description, List<Job> jobs, List<ProgramModule> programModules){
        this.Name = name;
        this.Description = description;
        this.Jobs = jobs;
        this.ProgramModules = programModules;
    }

    public void isAdmin(){

    }

    public ArrayList<HashMap<String, Object>> SelectModule(int id) {
        ArrayList<HashMap<String, Object>> Arguments = new ArrayList<>();

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql ="SELECT Name, Description FROM `module` WHERE Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while(rs.next()){
                System.out.println("---------------------------------------------------");

                HashMap<String, Object> argument = new HashMap<>();

                //int moduleId = rs.getInt("Id");
                String Nameid = rs.getString("Name");
                String Descriptionid = rs.getString("Description");

                //argument.put("Id", moduleId);
                argument.put("Nom", Nameid);
                argument.put("Description", Descriptionid);

                Arguments.add(argument);


                System.out.println("---------------------------------------------------");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
        // Close resources (stmt, conn, rs) in a finally block here
        // This is important to release database resources properly
        // and handle exceptions when closing resources.
        }
        return Arguments;

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

