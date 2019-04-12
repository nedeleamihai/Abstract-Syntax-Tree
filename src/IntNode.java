public class IntNode extends Leaf {

    public IntNode(String argument, int line) {
        super("<IntNode>", argument, line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}