import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        // Check if file exists
        File f = new File("sample.py");

        if (!f.exists() || f.isDirectory()) {
            System.out.println("File with given name does not exist.");
            return;
        }

        // Create an input file stream
        ANTLRFileStream input = new ANTLRFileStream("sample.py");

        // Create a lexer that feeds from that stream
        Python3Lexer lexer = new Python3Lexer(input);

        // Create a stream of tokens fed by the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        System.out.println("Number of tokens: " + tokens.getTokens().size());

        // Create a parser that feeds off the tokens buffer
        Python3Parser parser = new Python3Parser(tokens);

        // Begin parsing at file input
        ParseTree tree = parser.file_input();

        // Check if file is correct
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.out.println("Given file is not syntactically correct.");
            return;
        }

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();

        // Walk the tree created during the parse, trigger callbacks
        TypeErrorListener listener = new TypeErrorListener();
        walker.walk(listener, tree);

        ReportGenerator raport = new ReportGenerator(listener.getIssues());
    }
}

