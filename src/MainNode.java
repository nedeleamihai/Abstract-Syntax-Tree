public class MainNode extends Node {

    public MainNode() {
        super("<MainNode>", "", 0);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}