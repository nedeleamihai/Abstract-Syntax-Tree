public class NotNode extends Node {

    public NotNode(int line) {
        super("<NotNode> !", "", line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}