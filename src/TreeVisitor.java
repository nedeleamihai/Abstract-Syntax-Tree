public class TreeVisitor implements Visitor {

    private Visitor baseVisitor;

    public TreeVisitor(Visitor baseVisitor) {
        this.baseVisitor = baseVisitor;
    }

    public Visitor getBaseVisitor() {
        return baseVisitor;
    }

    @Override
    public void visit(MainNode p) {
        p.accept(baseVisitor);
        for (Visitable it : p.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(SequenceNode s) {
        s.accept(baseVisitor);
        for (Visitable it : s.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(IntNode i) {
        i.accept(baseVisitor);
    }

    @Override
    public void visit(BoolNode b) {
        b.accept(baseVisitor);
    }

    @Override
    public void visit(VarNode v) {
        v.accept(baseVisitor);
    }

    @Override
    public void visit(AssignmentNode a) {
        a.accept(baseVisitor);
        for (Visitable it : a.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(PlusNode p) {
        p.accept(baseVisitor);
        for (Visitable it : p.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(DivNode d) {
        d.accept(baseVisitor);
        for (Visitable it : d.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(BracketNode b) {
        b.accept(baseVisitor);
        for (Visitable it : b.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(IfNode i) {
        i.accept(baseVisitor);
        for (Visitable it : i.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(BlockNode b) {
        b.accept(baseVisitor);
        if(!b.IsSubNodesEmpty()) {
            for (Visitable it : b.getSubNodes()) {
                it.accept(this);
            }
        }
    }

    @Override
    public void visit(GreaterNode g) {
        g.accept(baseVisitor);
        for (Visitable it : g.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(NotNode n) {
        n.accept(baseVisitor);
        for (Visitable it : n.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(AndNode a) {
        a.accept(baseVisitor);
        for (Visitable it : a.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(WhileNode w) {
        w.accept(baseVisitor);
        for (Visitable it : w.getSubNodes()) {
            it.accept(this);
        }
    }
}
