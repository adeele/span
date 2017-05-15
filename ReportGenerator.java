import java.util.ArrayList;

public class ReportGenerator {
    public ReportGenerator(ArrayList<Issue> issues) {
        for (Issue i : issues) {
            System.out.println("At line " +
                    i.getLine() +
                    ": " +
                    translateIsCertain(i.getIsCertain()) +
                    " TypeError exception of Type " +
                    i.getType() +
                    ". " +
                    translateDetails(i.getType(), i.getDetails())
            );
        }
    }

    private String translateDetails(int type, String details) {
        switch (type) {
            case 1:
                return "Dangerous operation '" + details + "'.";

            case 2:
                return "Array index may not be an integer.";
        }

        return "Function " + details + " may not take given number of arguments.";
    }

    private String translateIsCertain(boolean isCertain) {
        return isCertain ? "certain" : "possible";
    }
}
