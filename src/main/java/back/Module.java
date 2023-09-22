package back;

import java.util.List;

public class Module {
    public String Name;
    public List<Lesson> Lessons;

    public Module (String name, List<Lesson> lessons){
        this.Name = name;
        this.Lessons = lessons;
    }
}
