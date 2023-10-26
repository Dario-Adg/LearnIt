package back;
public class Job {

    private int Id;
    public String Name;

    public Job (int id, String name){
        this.Id = id;
        this.Name = name;
    }

    //Getters
    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    // Setters
    public void setId(int id) {
        this.Id = id;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
