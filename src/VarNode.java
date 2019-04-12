public class VarNode extends Leaf {
    //Atunci cand AST-ul va fi interpretat valoarea din VarNode va fi stocata in value
    private String value;

    public VarNode(String argument, int line) {

        super("<VariableNode>", argument, line);
        this.value = null;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
         this.value = value;
    }

    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}