package back;

import java.util.List;

public class Program {
    public String Name;
    public List<Module> Modules;

    public Program (String name, List<Module> modules){
        this.Name = name;
        this.Modules = modules;
    }
}
