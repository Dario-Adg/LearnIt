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

    public User getUser() {
        return this.User;
    }

    public Program getProgram() {
        return this.Program;
    }

    public boolean isValid() {
        return this.IsValid;
    }

    public Date getEndDateProgram() {
        return this.EndDateProgram;
    }
}