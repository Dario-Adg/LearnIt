package back;

public class NoteModule {
    public User User;
    public Module Module;
    public int Note;
    public boolean IsValid;

    public NoteModule(User user, Module module, int note, boolean isValid){
        this.User = user;
        this.Module = module;
        this.Note = note;
        this.IsValid = isValid;
    }

    public User getUser() {
        return this.User;
    }

    public Module getModule() {
        return this.Module;
    }

    public boolean isValid() {
        return this.IsValid;
    }

    public int getNote() {
        return this.Note;
    }
}
