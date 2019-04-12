public class DivNode extends Node {

    public DivNode(int line) {

        super("<DivNode> /", "", line);
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}