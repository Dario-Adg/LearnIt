package back;

import java.util.List;

public class Program {
    public String Name;
    public List<ProgramModule> ProgramModules;

    public Program (String name, List<ProgramModule> programModules){
        this.Name = name;
        this.ProgramModules = programModules;
    }
}
