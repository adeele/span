import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;

import java.io.*;

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

        // Handle hard and soft parameter
        if (cmd.hasOption("hard") || cmd.hasOption("h")) {
            // TODO: do not filter
        } else if (cmd.hasOption("soft") || cmd.hasOption("s")) {
            // TODO: filter results for certain exceptions
        } else {
            // TODO: filter results only for Type 1 and Type 2
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

        File[] files;
        File directory;

        // Handle file and directory parameter
        if (cmd.hasOption("file") || cmd.hasOption("f")) {
            files = new File[]{ new File(cmd.getOptionValue("file")) };

            // Check if file exists
            if (!files[0].exists() || files[0].isDirectory()) {
                System.out.println("File with given name does not exist.");
                return;
            }
        } else {
            if (cmd.hasOption("directory") || cmd.hasOption("d")) {
                directory = new File(cmd.getOptionValue("directory"));

                if (directory.exists() && directory.isDirectory()) {
                    files = directory.listFiles();
                } else {
                    return;
                }
            } else {
                return;
            }
        }

        if (files.length == 0) {
            System.out.println("No files given to analyse.");
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
                break;
            }

            // Create a generic parse tree walker that can trigger callbacks
            ParseTreeWalker walker = new ParseTreeWalker();

            // Walk the tree created during the parse, trigger callbacks
            TypeErrorListener listener = new TypeErrorListener();
            walker.walk(listener, tree);

            ReportGenerator raport = new ReportGenerator(listener.getIssues(), file.getName(), bw);
        }

        // Close writing buffer
        bw.close();
    }
}

