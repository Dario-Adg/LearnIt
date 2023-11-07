package back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Job {
    SOFTWARE_ENGINEER(1, "Ingénieur logiciel"),
    WEB_DEVELOPER(2, "Développeur web"),
    DATA_ANALYST(3, "Analyste de données"),
    NETWORK_ENGINEER(4, "Ingénieur réseau"),
    DATABASE_ADMINISTRATOR(5, "Administrateur de base de données"),
    SYSTEM_ADMINISTRATOR(6, "Administrateur système"),
    SECURITY_ANALYST(7, "Analyste en sécurité informatique"),
    MOBILE_APP_DEVELOPER(8, "Développeur d'applications mobiles"),
    DEVOPS_ENGINEER(9, "Ingénieur DevOps"),
    QA_TESTER(10, "Testeur qualité logicielle");

    private final int Id;
    private final String FrenchLabel;

    Job(int id, String frenchLabel) {
        this.Id = id;
        this.FrenchLabel = frenchLabel;
    }

    public int GetId() {
        return this.Id;
    }

    public String GetFrenchLabel() {
        return this.FrenchLabel;
    }

    public static List<Job> GetAllJobs(){
        return new ArrayList<>(Arrays.asList(Job.values()));
    }

    public static String GetJobNamesSeparatedByCommas(List<Job> jobList) {

        return jobList.stream()
                .map(Job::GetFrenchLabel)
                .collect(Collectors.joining(", "));
    }
}
