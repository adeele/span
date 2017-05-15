import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

public class TypeErrorListener extends Python3BaseListener {
    private ArrayList<Issue> issues;

    public TypeErrorListener() {
        this.issues = new ArrayList<>();
    }

    @Override public void enterFile_input(Python3Parser.File_inputContext ctx) {
        // File input starts analyze
        System.out.println("Start");
    }

    @Override public void enterAugassign(Python3Parser.AugassignContext ctx) {
        // TODO: count certain
        issues.add(new Issue(ctx.start.getLine(), 1, true, ctx.children.get(0).getText()));
    }

    @Override public void enterComp_op(Python3Parser.Comp_opContext ctx) {
        // TODO: count certain
        String operator = ctx.children.get(0).getText();
        if (operator != "is" && operator != "is not") {
            issues.add(new Issue(ctx.start.getLine(), 1, true, operator));
        }
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }
}
