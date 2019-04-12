import java.util.*;

public class InterpretVisitor implements Visitor {

    private OutputWriter outputWriter;
    private ArrayList<VarNode> ListaVariabile;

    public InterpretVisitor(String filename, ArrayList<VarNode> ListaVariabile) {
        outputWriter = new OutputWriter(filename);
        this.ListaVariabile = ListaVariabile;
    }

    public void stop() {
        outputWriter.stopWritingToFile();
    }

    @Override
    public void visit(MainNode p) {
        for (Visitable it : p.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(SequenceNode s) {
        for (Visitable it : s.getSubNodes()) {
            it.accept(this);
        }
    }

    @Override
    public void visit(IntNode i) {

    }

    @Override
    public void visit(BoolNode b) {

    }

    @Override
    public void visit(VarNode v) {

    }

    @Override
    public void visit(AssignmentNode a) {
        a.getSubNodes().get(0).accept(this);
        a.getSubNodes().get(1).accept(this);

        int i, poz = 0;
        for(i = 0; i < ListaVariabile.size(); i++){
            if(((Leaf)a.getSubNodes().get(0)).getArgument().equals(ListaVariabile.get(i).getArgument())){
                poz = i;
            }
        }

        ListaVariabile.get(poz).setValue(ReturnValue(a.getSubNodes().get(1)));
    }

    @Override
    public void visit(PlusNode p) {
        p.getSubNodes().get(0).accept(this);
        p.getSubNodes().get(1).accept(this);

        int suma = Integer.parseInt(ReturnValue(p.getSubNodes().get(0))) +
                   Integer.parseInt(ReturnValue(p.getSubNodes().get(1)));
        p.setArgument("" + suma);
    }

    @Override
    public void visit(DivNode d) {
        d.getSubNodes().get(0).accept(this);
        d.getSubNodes().get(1).accept(this);

        if(Integer.parseInt(ReturnValue(d.getSubNodes().get(1))) == 0){
            writeError("DivideByZero", ((Leaf)d.getSubNodes().get(1)).getLine());
            System.exit(0);
        }

        int dif = Integer.parseInt(ReturnValue(d.getSubNodes().get(0))) /
                  Integer.parseInt(ReturnValue(d.getSubNodes().get(1)));
        d.setArgument("" + dif);
    }

    @Override
    public void visit(BracketNode b) {
        b.getSubNodes().get(0).accept(this);
        b.setArgument(((Leaf)b.getSubNodes().get(0)).getArgument());
    }

    @Override
    public void visit(IfNode i) {
        i.getSubNodes().get(0).accept(this);
        if(((Leaf)i.getSubNodes().get(0)).getArgument().equals("true")){
            i.getSubNodes().get(1).accept(this);
        }else{
            i.getSubNodes().get(2).accept(this);
        }
    }

    @Override
    public void visit(BlockNode b) {
        if(!b.IsSubNodesEmpty()) {
            b.getSubNodes().get(0).accept(this);
        }
    }

    @Override
    public void visit(GreaterNode g) {
        g.getSubNodes().get(0).accept(this);
        g.getSubNodes().get(1).accept(this);

        if(Integer.parseInt(ReturnValue(g.getSubNodes().get(0))) >
           Integer.parseInt(ReturnValue(g.getSubNodes().get(1)))){
            g.setArgument("true");
        }else{
            g.setArgument("false");
        }
    }

    @Override
    public void visit(NotNode n) {
        n.getSubNodes().get(0).accept(this);
        if(((Leaf)n.getSubNodes().get(0)).getArgument().equals("true")){
            n.setArgument("false");
        }else{
            n.setArgument("true");
        }
    }

    @Override
    public void visit(AndNode a) {
        a.getSubNodes().get(0).accept(this);
        a.getSubNodes().get(1).accept(this);

        if(((Leaf)a.getSubNodes().get(0)).getArgument().equals("true") &&
           ((Leaf)a.getSubNodes().get(1)).getArgument().equals("true")){
            a.setArgument("true");
        }else{
            a.setArgument("false");
        }
    }

    @Override
    public void visit(WhileNode w) {
        w.getSubNodes().get(0).accept(this);
        while(((Leaf)w.getSubNodes().get(0)).getArgument().equals("true")){
            w.getSubNodes().get(1).accept(this);
            w.getSubNodes().get(0).accept(this);
        }
    }

    public String ReturnValue(Visitable v){
        if(((Leaf) v).getName().equals("<VariableNode>")){
            for(VarNode var : ListaVariabile) {
                if(var.getArgument().equals(((VarNode) v).getArgument())){

                    if(((VarNode) var).getValue() != null &&  !((VarNode) var).getValue().isEmpty()){
                        return ((VarNode) var).getValue();
                    }

                    writeError("UnassignedVar", ((VarNode) v).getLine());
                    System.exit(0);
                }
            }
        }
        return ((Leaf) v).getArgument();
    }

    //Printam in fisier Lista de variabile
    public void writeListaVariabile(){
        for(VarNode var : ListaVariabile){
            outputWriter.writeStringToFile(var.getArgument() + "=" + var.getValue() + "\n");
        }
    }

    //Printam in fisier eroarea si linia unde a fost gasita eroarea
    public void writeError(String eroare, int line){
        outputWriter.writeStringToFile(eroare + " " + line + "\n");
    }
}
