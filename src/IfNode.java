public class IfNode extends Node {

    public IfNode(int line) {

        super("<IfNode> if", "", line);
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}