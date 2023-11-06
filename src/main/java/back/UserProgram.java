package back;

import java.util.Date;

public class UserProgram {
    private User User;
    private Program Program;
    private boolean IsValid;
    private Date EndDateProgram;

    public UserProgram(User user, Program program, boolean isValid, Date endDateProgram) {
        this.User = user;
        this.Program = program;
        this.IsValid = isValid;
        this.EndDateProgram = endDateProgram;
    }

    public User GetUser() {
        return this.User;
    }

    public Program GetProgram() {
        return this.Program;
    }

    public boolean IsValid() {
        return this.IsValid;
    }

    public Date GetEndDateProgram() {
        return this.EndDateProgram;
    }
}