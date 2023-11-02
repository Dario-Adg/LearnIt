package back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Program {
    private int Id;
    private String Name;
    private String Description;
    private String JobIds;
    private final List<Module> Modules= new ArrayList<>();
    private final List<UserProgram> ProgramUsers = new ArrayList<>();

    public Program (int id, String name, String description, String jobIds){
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.JobIds = jobIds;
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
        if (this.JobIds.isBlank()){
            return new ArrayList<>();
        }
        List<Integer> jobIds = Arrays.stream(this.JobIds.split(","))
                .map(String::trim) // Supprime les espaces éventuels autour des IDs
                .map(Integer::valueOf)
                .toList();

        // Supposez que vous ayez une liste de tous les jobs disponibles
        List<Job> allJobs = Job.GetAllJobs();

        return allJobs.stream()
                .filter(job -> jobIds.contains(job.getId()))
                .toList();
    }

    public List<Module> getModules() {
        return this.Modules;
    }

    public List<UserProgram>  getProgramUsers() {
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

    public static String setJobIds (List<Job> jobs){
        return jobs.stream()
                .map(Job::getId)
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public void addModule(Module module) {
        this.Modules.add(module);
    }
}
