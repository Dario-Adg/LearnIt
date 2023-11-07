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
    public int GetId() {
        return this.Id;
    }

    public String GetName() {
        return this.Name;
    }

    public String GetDescription() {
        return this.Description;
    }

    public List<Program> GetPrograms() {
        return this.Programs;
    }

    public List<Lesson> GetLessons() {
        return this.Lessons;
    }

    public List<NoteModule> GetNoteModules() {
        return this.NoteModules;
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

    public void AddProgram(Program program) {
        this.Programs.add(program);
    }

    public void AddLesson(Lesson lesson) {
        this.Lessons.add(lesson);
    }
}
