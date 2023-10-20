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
}
