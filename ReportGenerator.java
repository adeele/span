import java.io.*;
import java.util.ArrayList;

public class ReportGenerator {
    public ReportGenerator(ArrayList<Issue> issues, String fileName, BufferedWriter bw, Mode mode) throws IOException {
        bw.write("_In " + fileName + ":_  \n");

        int howManyCertain = 0;

        for (Issue i : issues) {
            if (i.getIsCertain()) {
                howManyCertain++;
            }
        }

        bw.write("**" + issues.size() + " warning(s) found, " + howManyCertain + " certain exceptions**  \n");

        for (Issue i : issues) {
            if (mode == Mode.HARD || (mode == Mode.NORMAL && i.getType() == 1) || (mode == Mode.SOFT && i.getIsCertain())) {
                bw.write("At line " +
                        i.getLine() +
                        ": **" +
                        translateIsCertain(i.getIsCertain()) +
                        "** _TypeError_ exception of _Type " +
                        i.getType() +
                        "_. " +
                        translateDetails(i.getType(), i.getDetails()) +
                        "  \n"
                );
            }
        }

        bw.write("\n");
    }

    private String translateDetails(int type, String details) {
        switch (type) {
            case 1:
                return "Dangerous operation `" + details + "`.";

            case 2:
                return "Array index `" + details + "` must be an integer.";
        }

        return "Function `" + details + "` may not take given number of arguments.";
    }

    private String translateIsCertain(boolean isCertain) {
        return isCertain ? "certain" : "possible";
    }
}
