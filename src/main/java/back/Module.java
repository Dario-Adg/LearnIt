package back;


import java.sql.*;
import java.util.List;

public class Module {
    public String Name;
    public String Description;
    public List<ModuleLesson> ModuleLessons;
    public Connection conn = null;
    public PreparedStatement stmt = null;
    public Module(String name, String description, List<ModuleLesson> moduleLessons) {

    }

    public String Module(String name, String description, List<ModuleLesson> moduleLessons) {
        this.Name = name;
        this.Description = description;
        this.ModuleLessons = moduleLessons;
    }

    public void AddModule() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "INSERT INTO module (Name, Description,ModuleLessons) VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, Description);
            stmt.setArray(2, (Array) ModuleLessons);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        


    public void DeleteModule() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "DELETE FROM module WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, Description);
            stmt.setArray(2, (Array) ModuleLessons);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



    public void ModifyModule(){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, Description);
            stmt.setArray(2, (Array) ModuleLessons);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public class ModuleLessonDeleter(Array){

        public boolean deleteModuleLesson(int moduleLessonId){

            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
                String sql = "DELETE FROM module_lessons WHERE id = ?";


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }




    }


















}

