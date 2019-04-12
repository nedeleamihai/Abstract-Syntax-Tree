public class AndNode extends Node {

    public AndNode(int line) {
        super("<AndNode> &&", "", line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}