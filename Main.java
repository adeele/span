import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create an input character stream
        ANTLRFileStream input = new ANTLRFileStream("sample.py");

        // Create an Lexer that feeds from that stream
        Python3Lexer lexer = new Python3Lexer(input);

        // Create a stream of tokens fed by the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        Python3Parser parser = new Python3Parser(tokens);

        // Begin parsing at rule prog
        ParseTree tree = parser.prog();

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();

        // Walk the tree created during the parse, trigger callbacks
        walker.walk(new Python3BaseListener(), tree);
        System.out.println(); // print a \n after translation
    }
}

