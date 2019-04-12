public class BoolNode extends Leaf {

    public BoolNode(String argument, int line) {
        super("<BoolNode>", argument, line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}