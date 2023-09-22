package back;

import java.util.List;

public class Program {
    public String Name;
    public String Description;
    public List<Job> Jobs;
    public List<ProgramModule> ProgramModules;

    public Program (String name, String description, List<Job> jobs, List<ProgramModule> programModules){
        this.Name = name;
        this.Description = description;
        this.Jobs = jobs;
        this.ProgramModules = programModules;
    }
}
