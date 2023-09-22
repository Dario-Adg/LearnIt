package back;

import java.util.Date;

public class UserProgram {
    public User User;
    public Program Program;
    public boolean IsValid;
    public Date StartDateProgram;

    public UserProgram(User user, Program program, boolean isValid, Date startDateProgram){
        this.User = user;
        this.Program = program;
        this.IsValid = isValid;
        this.StartDateProgram = startDateProgram;
    }
}
