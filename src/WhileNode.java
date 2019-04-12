public class WhileNode extends Node {

    public WhileNode(int line) {
        super("<WhileNode> while", "", line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}