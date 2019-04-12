abstract class Leaf implements Visitable {
    protected String name;
    protected String argument;
    protected Node parent;
    protected int depthLevel;
    protected int line;

    public Leaf(String name, String argument, int line) {
        this.name = name;
        this.argument = argument;
        this.line = line + 1;
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return argument;
    }

    public Node getParrent() {
        return parent;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public int getLine() {
        return line;
    }

    public void setArgument(String NewArgument) {
        this.argument = NewArgument;
    }

    public void setParrent(Node parent) {
        this.parent = parent;
        depthLevel = parent.getDepthLevel() + 1;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }
}