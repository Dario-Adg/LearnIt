package back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Module {
    private int Id;
    private String Name;
    private String Description;
    private final List<Program> Programs= new ArrayList<>();
    private final List<Lesson> Lessons= new ArrayList<>();
    private final List<NoteModule> NoteModules = new ArrayList<>();

    public Module(int id, String name, String description) {
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

    public List<Program> getPrograms() {
        return this.Programs;
    }

    public List<Lesson> getLessons() {
        return this.Lessons;
    }

    public List<NoteModule> getNoteModules() {
        return this.NoteModules;
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

    public void addProgram(Program program) {
        this.Programs.add(program);
    }

    public void addLesson(Lesson lesson) {
        this.Lessons.add(lesson);
    }
}
