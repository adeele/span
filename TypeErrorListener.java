import java.util.*;

public class TypeErrorListener extends Python3BaseListener {
    private ArrayList<Issue> issues;

    public TypeErrorListener() {
        this.issues = new ArrayList<>();
    }

    @Override public void enterAugassign(Python3Parser.AugassignContext ctx) {
        issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(0).getText()));
    }

    @Override public void enterComp_op(Python3Parser.Comp_opContext ctx) {
        String operator = ctx.children.get(0).getText();

        if (!operator.equals("is") && !operator.equals("is not")) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, operator));
        }
    }

    @Override public void enterArith_expr(Python3Parser.Arith_exprContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterTerm(Python3Parser.TermContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterExpr(Python3Parser.ExprContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterXor_expr(Python3Parser.Xor_exprContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterAnd_expr(Python3Parser.And_exprContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterShift_expr(Python3Parser.Shift_exprContext ctx) {
        if (ctx.children.size() > 1) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, ctx.children.get(1).getText()));
        }
    }

    @Override public void enterTrailer(Python3Parser.TrailerContext ctx) {
        String bracket = ctx.children.get(0).getText();

        if (bracket.equals("(")) {
            String function = ctx.parent.getChild(0).getText() + "()";
            issues.add(new Issue(ctx.start.getLine(), 3, false, function));
        }
    }

    @Override public void enterAtom(Python3Parser.AtomContext ctx) {
        String bracket = ctx.children.get(0).getText();

        if (bracket.equals("[") && ctx.parent.getChildCount() > 1) {
            String sibling = ctx.parent.getChild(1).getText();
            issues.add(new Issue(ctx.start.getLine(), 2, false, sibling));
        }
    }

    @Override public void enterFor_stmt(Python3Parser.For_stmtContext ctx) {
        String array = ctx.getChild(3).getText();
        issues.add(new Issue(ctx.start.getLine(), 1, false, array));
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }
}
