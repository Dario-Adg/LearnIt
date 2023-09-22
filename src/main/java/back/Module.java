package back;

import java.util.List;

public class Module {
    public String Name;
    public String Description;
    public List<ModuleLesson> ModuleLessons;

    public Module (String name, String description, List<ModuleLesson> moduleLessons){
        this.Name = name;
        this.Description = description;
        this.ModuleLessons = moduleLessons;
    }
}
