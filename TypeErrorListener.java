import java.util.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

public class TypeErrorListener extends Python3BaseListener {
    TypeErrorListener() {
        this.issues = new ArrayList<>();
    }

    ArrayList<Issue> getIssues() {
        return issues;
    }

    @Override public void enterAugassign(Python3Parser.AugassignContext ctx) {
        Danger dangerLevel = getDangerLever(ctx.parent);

        if (dangerLevel != Danger.NONE) {
            issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(0).getText()));
        }
    }

    @Override public void enterComp_op(Python3Parser.Comp_opContext ctx) {
        Danger dangerLevel = getDangerLever(ctx.parent);
        String operator = ctx.children.get(0).getText();

        if (dangerLevel != Danger.NONE && !operator.equals("is") && !operator.equals("is not")) {
            issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, operator));
        }
    }

    @Override public void enterPower(Python3Parser.PowerContext ctx) {
        if (ctx.children.size() > 1 && ctx.children.get(1).getText().equals("**")) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, "**"));
            }
        }
    }

    @Override public void enterArith_expr(Python3Parser.Arith_exprContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
        }
    }

    @Override public void enterTerm(Python3Parser.TermContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
        }
    }

    @Override public void enterExpr(Python3Parser.ExprContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
        }
    }

    @Override public void enterXor_expr(Python3Parser.Xor_exprContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
        }
    }

    @Override public void enterAnd_expr(Python3Parser.And_exprContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
        }
    }

    @Override public void enterShift_expr(Python3Parser.Shift_exprContext ctx) {
        if (ctx.children.size() > 1) {
            Danger dangerLevel = getDangerLever(ctx);

            if (dangerLevel != Danger.NONE) {
                issues.add(new Issue(ctx.start.getLine(), 1, dangerLevel == Danger.CERTAIN, ctx.children.get(1).getText()));
            }
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
            ParserRuleContext sibling = (ParserRuleContext) ctx.parent.getChild(1);
            ParserRuleContext innerSibling = (ParserRuleContext) sibling.getChild(1);
            int type = innerSibling.getStart().getType();

            if (type == 35) {
                issues.add(new Issue(ctx.start.getLine(), 2, false, sibling.getText()));
            } else if (type != 38) {
                issues.add(new Issue(ctx.start.getLine(), 2, true, sibling.getText()));
            }
        }
    }

    @Override public void enterFor_stmt(Python3Parser.For_stmtContext ctx) {
        String array = ctx.getChild(3).getText();
        ParserRuleContext p = (ParserRuleContext) ctx.getChild(3);
        int type = p.getStart().getType();

        if (type == 35) {
            issues.add(new Issue(ctx.start.getLine(), 1, false, array + " must be iterable"));
        } else if (type != 54) {
            issues.add(new Issue(ctx.start.getLine(), 1, true, array + " must be iterable"));
        }
    }

    private ArrayList<Issue> issues;
    private enum Danger { NONE, POSSIBLE, CERTAIN }

    private Danger getDangerLever(RuleContext ctx) {
        ParserRuleContext arg1 = (ParserRuleContext) ctx.getChild(0);
        ParserRuleContext arg2 = (ParserRuleContext) ctx.getChild(2);

        int type1 = arg1.getStart().getType();
        int type2 = arg2.getStart().getType();

        // Exception may be thrown if types are variables
        if (type1 == 35 || type2 == 35) {
            return Danger.POSSIBLE;
        }

        // Exception will be thrown if types are different and one of them is string or byte
        if (type1 != type2 && ((type1 == 36 || type2 == 36) || (type1 == 37 || type2 == 37))) {
            return Danger.CERTAIN;
        }

        return Danger.NONE;
    }
}
