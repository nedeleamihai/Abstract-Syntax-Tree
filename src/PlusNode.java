public class PlusNode extends Node {

    public PlusNode(int line) {

        super("<PlusNode> +", "", line);
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}