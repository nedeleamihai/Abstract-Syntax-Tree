public class PrintVisitor implements Visitor {

    private OutputWriter outputWriter;

    public PrintVisitor(String filename) {
        outputWriter = new OutputWriter(filename);
    }

    public void stop() {
        outputWriter.stopWritingToFile();
    }

    @Override
    public void visit(MainNode p) {
        writeTabs(p.getDepthLevel());
        outputWriter.writeStringToFile(p.getName() + "\n");
    }

    @Override
    public void visit(SequenceNode s) {
        writeTabs(s.getDepthLevel());
        outputWriter.writeStringToFile(s.getName() + "\n");
    }

    @Override
    public void visit(IntNode i) {
        writeTabs(i.getDepthLevel());
        outputWriter.writeStringToFile(i.getName() + " " + i.getArgument() + "\n");
    }

    @Override
    public void visit(BoolNode b) {
        writeTabs(b.getDepthLevel());
        outputWriter.writeStringToFile(b.getName() + " " + b.getArgument() + "\n");
    }

    @Override
    public void visit(VarNode v) {
        writeTabs(v.getDepthLevel());
        outputWriter.writeStringToFile(v.getName() + " " + v.getArgument() + "\n");
    }

    @Override
    public void visit(AssignmentNode a) {
        writeTabs(a.getDepthLevel());
        outputWriter.writeStringToFile(a.getName() + "\n");
    }

    @Override
    public void visit(PlusNode p) {
        writeTabs(p.getDepthLevel());
        outputWriter.writeStringToFile(p.getName() + "\n");
    }

    @Override
    public void visit(DivNode d) {
        writeTabs(d.getDepthLevel());
        outputWriter.writeStringToFile(d.getName() + "\n");
    }

    @Override
    public void visit(BracketNode b) {
        writeTabs(b.getDepthLevel());
        outputWriter.writeStringToFile(b.getName() + "\n");
    }

    @Override
    public void visit(IfNode i) {
        writeTabs(i.getDepthLevel());
        outputWriter.writeStringToFile(i.getName() + "\n");
    }

    @Override
    public void visit(BlockNode b) {
        writeTabs(b.getDepthLevel());
        outputWriter.writeStringToFile(b.getName() + "\n");
    }

    @Override
    public void visit(GreaterNode g) {
        writeTabs(g.getDepthLevel());
        outputWriter.writeStringToFile(g.getName() + "\n");
    }

    @Override
    public void visit(NotNode n) {
        writeTabs(n.getDepthLevel());
        outputWriter.writeStringToFile(n.getName() + "\n");
    }

    @Override
    public void visit(AndNode a) {
        writeTabs(a.getDepthLevel());
        outputWriter.writeStringToFile(a.getName() + "\n");
    }

    @Override
    public void visit(WhileNode w) {
        writeTabs(w.getDepthLevel());
        outputWriter.writeStringToFile(w.getName() + "\n");
    }

    public void writeTabs(int NrTabs){
        for (int i = 0; i < NrTabs; i++) {
            outputWriter.writeStringToFile("\t");
        }
    }
}