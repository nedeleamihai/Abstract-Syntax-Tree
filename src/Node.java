import java.util.*;

abstract class Node extends Leaf {
    protected ArrayList<Visitable> subNodes;

    public Node(String name, String argument, int line) {
        super(name, argument, line);
        subNodes = new ArrayList<Visitable>();
        parent = null;
    }

    public boolean IsSubNodesEmpty() {
        if(subNodes.isEmpty()){
            return true;
        }
        return false;
    }

    public ArrayList<Visitable> getSubNodes() {
        if (!subNodes.isEmpty()) {
            return subNodes;
        }
        return null;
    }

    //Adauga un subnod(frunza sau nod)
    protected void addSubNode(Leaf subordinate) {
        subordinate.setParrent(this);
        subordinate.setDepthLevel(this.getDepthLevel() + 1);
        subNodes.add(subordinate);
    }

    @Override
    public void setParrent(Node parent) {
        super.setParrent(parent);
        updateDepth();
    }

    //Updatez depthLevelul copiilor nodului in functie de nivelul parintelui
    protected void updateDepth() {
        for (Visitable it : subNodes) {
            ((Leaf) it).setDepthLevel(depthLevel + 1);
        }
    }

    //Daca s-a creeat un nou SequenceNode, updatam adacimea fiecarui Node cu o pozitie
    protected void restoreDepth() {
        updateDepth();
        for (Visitable it : subNodes) {
            if (((Leaf) it).getName().equals("<VariableNode>") ||
                ((Leaf) it).getName().equals("<IntNode>") ||
                ((Leaf) it).getName().equals("<BoolNode>")){

                updateDepth();
            }else {
                ((Node) it).restoreDepth();
            }
        }
    }
}