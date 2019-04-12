public class SequenceNode extends Node {

    public SequenceNode() {
        super("<SequenceNode>", "", 0);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}