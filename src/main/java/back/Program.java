package back;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private int Id;
    private String Name;
    private String Description;
    private final List<Job> Jobs = new ArrayList<>();
    private final List<Module> Modules= new ArrayList<>();
    private final List<UserProgram> ProgramUsers = new ArrayList<>();

    public Program (int id, String name, String description){
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

    public List<Job> getJobs() {
        return this.Jobs;
    }

    public List<Module> getModules() {
        return this.Modules;
    }

    public List<UserProgram> getProgramUsers() {
        return this.ProgramUsers;
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

    public void addJob(Job job) {
        Jobs.add(job);
    }

    public void removeJob(Job job) {
        Jobs.remove(job);
    }

    public void addModule(Module module) {
        this.Modules.add(module);
    }
}
