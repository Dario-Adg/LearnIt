package back;

import java.util.Date;

public class UserProgram {
    public User User;
    public Program Program;
    public boolean IsValid;
    public Date EndDateProgram;

    public UserProgram(User user, Program program, boolean isValid, Date endDateProgram){
        this.User = user;
        this.Program = program;
        this.IsValid = isValid;
        this.EndDateProgram = endDateProgram;
    }
}
