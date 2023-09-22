package back;

public class User {
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public boolean IsAdmin;

    public User (String firstName, String lastName, String email, String password, boolean isAdmin){
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.IsAdmin = isAdmin;
    }
}
