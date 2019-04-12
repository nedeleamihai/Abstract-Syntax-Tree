public class GreaterNode extends Node {

    public GreaterNode(int line) {
        super("<GreaterNode> >", "", line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}