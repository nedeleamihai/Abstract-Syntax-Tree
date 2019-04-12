public class BlockNode extends Node {

    public BlockNode(int line) {

        super("<BlockNode> {}", "", line);
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}