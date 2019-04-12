import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer l = new Lexer(new FileReader("input"));
        l.yylex();

        // Print-ul structurii ast-ului
        Visitor v = new PrintVisitor("arbore");
        Visitor tree = new TreeVisitor(v);
        l.ast.getRootNode().accept(tree);
        ((PrintVisitor) v).stop();

        // Interpretarea ast-ului
        v = new InterpretVisitor("output", l.ListaVariabile);
        // Daca in timpul creeari AST-ului am gasit o UnassignedVar afisez eroarea in fisier si nu m-ai interpretez AST-ul
        if(l.EroareUnassignedVar != null) {
            ((InterpretVisitor) v).writeError(l.EroareUnassignedVar.getArgument(), l.EroareUnassignedVar.getLine());
            ((InterpretVisitor) v).stop();
        }else{
            l.ast.getRootNode().accept(v);
            ((InterpretVisitor) v).writeListaVariabile();
            ((InterpretVisitor) v).stop();
        }
    }
}
