package back;

import java.util.List;

public class User {
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public boolean IsAdmin;
    public boolean IsJobSeeker;
    public int DiplomaNumber;
    public List<UserProgram> UserPrograms;

    public User (String firstName, String lastName, String email, String password, boolean isAdmin,
                 boolean isJobSeeker, int DiplomaNumber, List<UserProgram> userPrograms){
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.IsAdmin = isAdmin;
        this.IsJobSeeker = isJobSeeker;
        this.DiplomaNumber = DiplomaNumber;
        this.UserPrograms = userPrograms;
    }
}
