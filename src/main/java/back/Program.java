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
    public int GetId() {
        return this.Id;
    }

    public String GetName() {
        return this.Name;
    }

    public String GetDescription() {
        return this.Description;
    }

    public List<Job> GetJobs() {
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
                .filter(job -> jobIds.contains(job.GetId()))
                .toList();
    }

    public List<Module> GetModules() {
        return this.Modules;
    }

    public List<UserProgram> GetProgramUsers() {
        return this.ProgramUsers;
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

    public static String SetJobIds (List<Job> jobs){
        return jobs.stream()
                .map(Job::GetId)
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public void AddModule(Module module) {
        this.Modules.add(module);
    }
}
