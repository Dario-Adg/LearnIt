package back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private boolean IsAdmin;
    private boolean IsJobSeeker;
    private int DiplomaNumber;
    private Date DateOfBirth;
    private final List<UserProgram> UserPrograms = new ArrayList<>();
    private final List<NoteModule> NoteModules = new ArrayList<>();

    public User (int id, String firstName, String lastName){
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public User (int id, String firstName, String lastName, String email, String password, boolean isJobSeeker,
                 int diplomaNumber, Date dateOfBirth){
        this(id, firstName, lastName);
        this.Email = email;
        this.Password = password;
        this.IsJobSeeker = isJobSeeker;
        this.DiplomaNumber = diplomaNumber;
        this.DateOfBirth = dateOfBirth;
    }

    public User (int id, String firstName, String lastName, String email, String password, boolean isAdmin,
                 boolean isJobSeeker, int diplomaNumber, Date dateOfBirth){
        this(id, firstName, lastName, email, password, isJobSeeker, diplomaNumber, dateOfBirth);
        this.IsAdmin = isAdmin;
    }


    //Getters
    public int GetId() {
        return this.Id;
    }

    public String GetFirstName() {
        return this.FirstName;
    }

    public String GetLastName() {
        return this.LastName;
    }

    public String GetFirstNameLastName(){
        return this.GetFirstName() + " " + this.GetLastName();
    }
    public String GetLastNameFirstName(){
        return this.GetLastName() + " " + this.GetFirstName();
    }

    public String GetEmail() {
        return this.Email;
    }

    public String GetPassword() {
        return this.Password;
    }

    public boolean GetIsAdmin() {
        return this.IsAdmin;
    }

    public boolean GetIsJobSeeker() {
        return this.IsJobSeeker;
    }

    public Integer GetDiplomaNumber() {
        return this.DiplomaNumber;
    }

    public Date GetDateOfBirth() {
        return this.DateOfBirth;
    }

    public List<UserProgram> GetUserPrograms() {
        return this.UserPrograms;
    }

    public List<NoteModule> GetNoteModules() {
        return this.NoteModules;
    }

    //Setters
    public void SetId(int id) {
        this.Id = id;
    }

    public void SetFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public void SetLastName(String lastName) {
        this.LastName = lastName;
    }

    public void SetEmail(String email) {
        this.Email = email;
    }

    public void SetPassword(String password) {
        this.Password = password;
    }

    public void SetIsAdmin(boolean isAdmin) {
        this.IsAdmin = isAdmin;
    }

    public void SetIsJobSeeker(boolean isJobSeeker) {
        this.IsJobSeeker = isJobSeeker;
    }

    public void SetDiplomaNumber(int diplomaNumber) {
        this.DiplomaNumber = diplomaNumber;
    }

    public void SetDateOfBirth(Date dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public void AddProgram(Program program, boolean isValid, Date endDateProgram) {
        UserProgram userProgram = new UserProgram(this, program, isValid, endDateProgram);
        this.UserPrograms.add(userProgram);
        program.GetProgramUsers().add(userProgram);
    }

    public void AddNoteModule(Module module, int note, boolean isValid) {
        NoteModule noteModule = new NoteModule(this, module, note, isValid);
        this.NoteModules.add(noteModule);
        module.GetNoteModules().add(noteModule);
    }
}
