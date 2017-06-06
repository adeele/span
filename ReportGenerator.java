import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportGenerator {
    public ReportGenerator(ArrayList<Issue> issues, File file, BufferedWriter bw, Mode mode, int numberOfLines) throws IOException {
        bw.write("_In " + file.getName() + ":_  \n");

        int howManyCertain = 0;

        for (Issue i : issues) {
            if (i.getIsCertain()) {
                howManyCertain++;
            }
        }

        bw.write("**" + issues.size() + " warning(s) found, " + howManyCertain + " certain exceptions**  \n");

        String dangerLevel = "LOW";
        double ratio = (double) howManyCertain / (double) issues.size();

        if (ratio > (double) 2/3) {
            dangerLevel = "HIGH";
        } else if (ratio > (double) 1/3) {
            dangerLevel = "MEDIUM";
        }

        bw.write("**" + (int) ((double) issues.size() / (double) numberOfLines * 100) + "% dangerous, classified to " + dangerLevel + " level of danger**  \n");

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

        String line;
        bw.write("\nFile reference:  \n```  \n");

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(int i = 1; (line = br.readLine()) != null; i++) {
                bw.write(i + ": " + line + "\n");
            }
        }

        bw.write("  \n```  \n");
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
