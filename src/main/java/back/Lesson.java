package back;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private int Id;
    private String Name;
    private String Description;
    private final List<Module> Modules = new ArrayList<>();

    public Lesson (int id, String name, String description) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
    }

    //Getters
    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public List<Module> getModules() {
        return this.Modules;
    }

    // Setters
    public void setId(int id) {
        this.Id = id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void addModule(Module module) {
        this.Modules.add(module);
    }
}
