import java.util.ArrayList;

public class ReportGenerator {
    public ReportGenerator(ArrayList<Issue> issues) {
        for (Issue i : issues) {
            System.out.println("" + i.getLine() + ',' + i.getType() + ',' + i.getIsCertain() + ',' + i.getDetails());
        }
    }
}
