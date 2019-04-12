public interface Visitor {
    public void visit(MainNode m);
    public void visit(SequenceNode s);
    public void visit(IntNode i);
    public void visit(BoolNode b);
    public void visit(VarNode v);
    public void visit(AssignmentNode a);
    public void visit(PlusNode p);
    public void visit(DivNode d);
    public void visit(BracketNode b);
    public void visit(IfNode i);
    public void visit(BlockNode b);
    public void visit(GreaterNode g);
    public void visit(NotNode n);
    public void visit(AndNode a);
    public void visit(WhileNode w);
}