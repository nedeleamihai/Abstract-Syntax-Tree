public class BracketNode extends Node {

    public BracketNode(int line) {

        super("<BracketNode> ()", "", line);
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}