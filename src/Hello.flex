import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

%%
 
%class Lexer
%standalone
%line
%int
WhiteSpaces = [\t\n]+
%{

  	ArrayList<VarNode> ListaVariabile = new ArrayList<>();
	ArrayList<String> Expression = new ArrayList<>();
	ArrayList<String> Prefix;
	VarNode EroareUnassignedVar = null;
	Ast ast = new Ast();

  	void CitesteListaInitiala(String StringInput){
		StringTokenizer st = new StringTokenizer(StringInput);
		st.nextToken(); 
		ListaVariabile.add(new VarNode(st.nextToken(), 0));		
	}
	
	void CreateNodes(){
		InfixPrefix InfPrf = new InfixPrefix();
		Prefix = InfPrf.InfixToPrefix(Expression);
		Expression.clear();

		while(!Prefix.isEmpty()){
			CreateNodesUtil(Prefix.remove(0));
			if(!ast.getCurrentNode().IsSubNodesEmpty() && ast.getCurrentNode().getSubNodes().size() > 2){
				CreateSequence();
			}
		}
	}

	void CreateSequence(){
		SequenceNode sqN = new SequenceNode();
        Visitable child2 = ast.getCurrentNode().getSubNodes().remove(ast.getCurrentNode().getSubNodes().size() - 1);
        Visitable child1 = ast.getCurrentNode().getSubNodes().remove(ast.getCurrentNode().getSubNodes().size() - 1);
        ast.getCurrentNode().addSubNode(sqN);
        ast.setCurrentNode(sqN);

		sqN.addSubNode((Node)child1);
		sqN.addSubNode((Node)child2);
        sqN.restoreDepth();
	}

	void CreateNodesUtil(String Node){

		if('a' <= Node.charAt(0) && Node.charAt(0) <= 'z'){
			VarNode varNode = new VarNode(Node, yyline);
			ast.getCurrentNode().addSubNode(varNode);
			
			if(EroareUnassignedVar == null){
				int sem = 0;
				for(VarNode var : ListaVariabile){
					if(var.getArgument().equals(varNode.getArgument())){
						sem = 1;
					}
				}
				if(sem == 0){
					EroareUnassignedVar = new VarNode("UnassignedVar", yyline);
				}
			}
		}

		if('0' <= Node.charAt(0) && Node.charAt(0) <= '9'){
			IntNode intNode = new IntNode(Node, yyline);
			ast.getCurrentNode().addSubNode(intNode);
		}

		if('A' <= Node.charAt(0) && Node.charAt(0) <= 'Z'){
			if(Node.equals("T")){
				Node = "true";
			}else{
				Node = "false";
			}
			BoolNode boolNode = new BoolNode(Node, yyline);
			ast.getCurrentNode().addSubNode(boolNode);
		}

		if(Node.equals("=")){
			AssignmentNode assignmentNode = new AssignmentNode(yyline);
			ast.addNodeToCurrent(assignmentNode);
			ast.setCurrentNode(assignmentNode);
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Copilul2
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("+")){
			PlusNode plusNode = new PlusNode(yyline);
			ast.addNodeToCurrent(plusNode);
			ast.setCurrentNode(plusNode);
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Copilul2
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("/")){
			DivNode divNode = new DivNode(yyline);
			ast.addNodeToCurrent(divNode);
			ast.setCurrentNode(divNode);	
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Copilul2
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals(">")){
			GreaterNode greaterNode = new GreaterNode(yyline);
			ast.addNodeToCurrent(greaterNode);
			ast.setCurrentNode(greaterNode);	
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Copilul2
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("!")){
			NotNode notNode = new NotNode(yyline);
			ast.addNodeToCurrent(notNode);
			ast.setCurrentNode(notNode);	
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("&")){
			AndNode andNode = new AndNode(yyline);
			ast.addNodeToCurrent(andNode);
			ast.setCurrentNode(andNode);	
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Copilul2
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("(")){
			BracketNode bracketNode = new BracketNode(yyline);
			ast.addNodeToCurrent(bracketNode);
			ast.setCurrentNode(bracketNode);
			CreateNodesUtil(Prefix.remove(0));//Copilul1
			CreateNodesUtil(Prefix.remove(0));//Paranteza inchisa
		}

		if(Node.equals(")")){
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}
	}

	void CreateNodesInstructions(String Node){
		if(Node.equals("if")){
			IfNode ifNode = new IfNode(yyline);
			ast.addNodeToCurrent(ifNode);
			ast.setCurrentNode(ifNode);
		}

		if(Node.equals("while")){
			WhileNode whileNode = new WhileNode(yyline);
			ast.addNodeToCurrent(whileNode);
			ast.setCurrentNode(whileNode);
		}

		if(Node.equals("{")){
			BlockNode blockNode = new BlockNode(yyline);
			ast.addNodeToCurrent(blockNode);
			ast.setCurrentNode(blockNode);
		}

		if(Node.equals("else")){
			ast.setCurrentNode(ast.getCurrentNode().getParrent());
		}

		if(Node.equals("}")){ //Sunt pe BlockNode
			if(!ast.getCurrentNode().IsSubNodesEmpty() && ast.getCurrentNode().getSubNodes().size() == 2){
				CreateSequence();
				ast.setCurrentNode(ast.getCurrentNode().getParrent());
			}
			ast.setCurrentNode(ast.getCurrentNode().getParrent().getParrent());
			if(!ast.getCurrentNode().IsSubNodesEmpty() && ast.getCurrentNode().getSubNodes().size() > 2){
				CreateSequence();
			}
		}
	}
	
%}

Var = [a-z]+
AVal = [1-9][0-9]* | 0
BVal ="true"|"false"

VarList ="int "{Var}|", "{Var}
Equal ="="
Comma =";"
Plus ="+"
Divide ="/"
Greater =">"
Not ="!"
And ="&&"
OpenPar ="("
ClosePar =")"
If ="if"
While ="while"
BlockOpen ="{"
Else ="} else"
BlockClose ="}"
 
%%

{VarList}	{ CitesteListaInitiala(yytext()); }

{If} {CreateNodesInstructions("if"); }
{BlockOpen} {CreateNodes(); 
			 CreateNodesInstructions("{"); }
{Else} {CreateNodesInstructions("else"); }
{BlockClose} {CreateNodesInstructions("}"); }
{While} {CreateNodesInstructions("while"); }

{Var}	{Expression.add(yytext()); }
{AVal}	{Expression.add(yytext()); }
{BVal}	{Expression.add(yytext()); }
{Equal}	{Expression.add("="); }
{Plus}	{Expression.add("+"); }
{Divide}	{Expression.add("/"); }
{Greater}	{Expression.add(">"); }
{Not}	{Expression.add("!"); }
{And}	{Expression.add("&"); }
{OpenPar}	{Expression.add("("); }
{ClosePar}	{Expression.add(")"); }
{Comma}	{CreateNodes(); }


{WhiteSpaces}	{ }
. {}


