public class Ast {
    private MainNode root;
    private SequenceNode sqN;
    private Node current;

    public Ast() {
        root = new MainNode();
        root.setDepthLevel(0);
        sqN = new SequenceNode();
        root.addSubNode(sqN);
        current = sqN;
    }

    public Node getRootNode() {
        return root;
    }

    public Node getCurrentNode() {
        return current;
    }

    public void setCurrentNode(Node newCurrent) {
        current = newCurrent;
    }

    public void addNodeToCurrent(Node newNode) {
        current.addSubNode(newNode);
    }

}
