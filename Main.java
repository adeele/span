import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

enum Mode { SOFT, NORMAL, HARD }

public class Main {
    public static void main(String[] args) throws Exception {

        // Set up console options
        Options options = new Options();
        options.addOption(new Option("", "help", false, "provides help"));
        options.addOption(new Option("f", "file", true, "points file to analyse"));
        options.addOption(
                new Option("d", "directory", true, "points directory to analyse"));
        options.addOption(new Option("o", "output", true, "points report file name"));
        options.addOption(new Option("s", "soft", false, "forces soft analyse"));
        options.addOption(new Option("h", "hard", false, "forces hard analyse"));

        // Create command line parser for options
        CommandLineParser cmdparser = new DefaultParser();
        CommandLine cmd = cmdparser.parse(options, args);

        // Handle help parameter
        if (cmd.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("SPAN - Static Python ANalyser", options);
            return;
        }

        Mode mode = Mode.NORMAL;

        // Handle hard and soft parameter
        if (cmd.hasOption("hard") || cmd.hasOption("h")) {
            mode = Mode.HARD;
        } else if (cmd.hasOption("soft") || cmd.hasOption("s")) {
            mode = Mode.SOFT;
        }

        String output = "";

        // Handle output parameter
        if (cmd.hasOption("output") || cmd.hasOption("o")) {
            output = cmd.getOptionValue("output");
        }

        if (output.equals("")) {
            output = "report";
        }

        File fout = new File(output + ".md");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        ArrayList<File> files = new ArrayList<File>();
        File directory;

        // Handle file and directory parameter
        if (cmd.hasOption("file") || cmd.hasOption("f")) {
            files.add(new File(cmd.getOptionValue("file")));

            File f = files.get(0);

            // Check if file exists
            if (!files.get(0).exists() || files.get(0).isDirectory()) {
                System.out.println("File " + files.get(0).getName() + " does not exist.");
                return;
            }

            if (!files.get(0).getName().endsWith(".py")) {
                System.out.println("File " + files.get(0).getName() + " is not Python file.");
                return;
            }
        } else {
            if (cmd.hasOption("directory") || cmd.hasOption("d")) {
                directory = new File(cmd.getOptionValue("directory"));

                if (directory.exists() && directory.isDirectory()) {
                    String[] extensions = { "py" };
                    files = new ArrayList<>(FileUtils.listFiles(directory, extensions, true));
                } else {
                    System.out.println("Directory " + directory.getName() + " does not exist.");
                    return;
                }
            } else {
                System.out.println("No files given to analyse.");
                return;
            }
        }

        for (File file : files) {
            // Create an input file stream
            ANTLRFileStream input = new ANTLRFileStream(file.getAbsolutePath());

            // Create a lexer that feeds from that stream
            Python3Lexer lexer = new Python3Lexer(input);

            // Create a stream of tokens fed by the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();

            // Create a parser that feeds off the tokens buffer
            Python3Parser parser = new Python3Parser(tokens);

            // Begin parsing at file input
            ParseTree tree = parser.file_input();

            // Check if file is correct
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.out.println("File " + file.getName() + " is not syntactically correct.");
                continue;
            }

            // Create a generic parse tree walker that can trigger callbacks
            ParseTreeWalker walker = new ParseTreeWalker();

            // Walk the tree created during the parse, trigger callbacks
            TypeErrorListener listener = new TypeErrorListener();
            walker.walk(listener, tree);

            LineNumberReader  lnr = new LineNumberReader(new FileReader(file));
            lnr.skip(Long.MAX_VALUE);

            ReportGenerator raport = new ReportGenerator(listener.getIssues(), file, bw, mode, lnr.getLineNumber());

            lnr.close();
        }

        // Close writing buffer
        bw.close();
    }
}

