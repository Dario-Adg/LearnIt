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
    public int getId() {
        return this.Id;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public String getFirstNameLastName(){
        return this.getFirstName() + " " + this.getLastName();
    }
    public String getLastNameFirstName(){
        return this.getLastName() + " " + this.getFirstName();
    }

    public String getEmail() {
        return this.Email;
    }

    public String getPassword() {
        return this.Password;
    }

    public boolean getIsAdmin() {
        return this.IsAdmin;
    }

    public boolean getIsJobSeeker() {
        return this.IsJobSeeker;
    }

    public Integer getDiplomaNumber() {
        return this.DiplomaNumber;
    }

    public Date getDateOfBirth() {
        return this.DateOfBirth;
    }

    public List<UserProgram> getUserPrograms() {
        return this.UserPrograms;
    }

    public List<NoteModule> getNoteModules() {
        return this.NoteModules;
    }

    //Setters
    public void setId(int id) {
        this.Id = id;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.IsAdmin = isAdmin;
    }

    public void setIsJobSeeker(boolean isJobSeeker) {
        this.IsJobSeeker = isJobSeeker;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.DiplomaNumber = diplomaNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public void addProgram(Program program, boolean isValid, Date endDateProgram) {
        UserProgram userProgram = new UserProgram(this, program, isValid, endDateProgram);
        this.UserPrograms.add(userProgram);
        program.getProgramUsers().add(userProgram);
    }

    public void addNoteModule(Module module, int note, boolean isValid) {
        NoteModule noteModule = new NoteModule(this, module, note, isValid);
        this.NoteModules.add(noteModule);
        module.getNoteModules().add(noteModule);
    }
}
