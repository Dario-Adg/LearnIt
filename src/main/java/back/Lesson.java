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
    public int GetId() {
        return this.Id;
    }

    public String GetName() {
        return this.Name;
    }

    public String GetDescription() {
        return this.Description;
    }

    public List<Module> GetModules() {
        return this.Modules;
    }

    // Setters
    public void SetId(int id) {
        this.Id = id;
    }

    public void SetName(String name) {
        this.Name = name;
    }

    public void SetDescription(String description) {
        this.Description = description;
    }

    public void AddModule(Module module) {
        this.Modules.add(module);
    }
}
