import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ReportGenerator {
    public ReportGenerator(ArrayList<Issue> issues, String fileName, String output) {
        PrintStream out = null;

        if (output == "") {
            output = "report";
        }

        try {
            out = new PrintStream(new FileOutputStream(output + ".md"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.setOut(out);
        System.out.println("_In " + fileName + ":_");

        int howManyCertain = 0;

        for (Issue i : issues) {
            if (i.getIsCertain()) {
                howManyCertain++;
            }
        }


        System.out.println("**" + issues.size() + " warning(s) found, " + howManyCertain + " certain exceptions**");

        for (Issue i : issues) {
            System.out.println("At line " +
                    i.getLine() +
                    ": **" +
                    translateIsCertain(i.getIsCertain()) +
                    "** _TypeError_ exception of _Type " +
                    i.getType() +
                    "_. " +
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
