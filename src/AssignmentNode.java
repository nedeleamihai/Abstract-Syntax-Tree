public class AssignmentNode extends Node {

    public AssignmentNode(int line) {

        super("<AssignmentNode> =", "", line);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}