package back;

import java.sql.*;
import java.util.List;

public class Module {
    public String Name;
    //public List<Lesson> Lessons;
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public Module (String name ){//List<Lesson> lessons
        this.Name = name;
        //this.Lessons = lessons;
    }

    public void AddLesson() {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/learnit", "root", "");
            String sql = "INSERT INTO module (Name, Lessons) VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            //stmt.setArray(2, (Array) Lessons);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delateLesson(int id) {

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
