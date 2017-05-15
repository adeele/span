public class Issue {
    private int line;
    private int type;
    private boolean isCertain;
    private String details;

    public Issue(int line, int type, boolean isCertain, String details) {
        this.line = line;
        this.type = type;
        this.isCertain = isCertain;
        this.details = details;
    }

    public int getLine() {
        return line;
    }

    public int getType() {
        return type;
    }

    public boolean getIsCertain() {
        return isCertain;
    }

    public String getDetails() {
        return details;
    }
}
