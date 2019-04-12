Am folosit Visitor Pattern pentru printarea si interpretarea AST-ului creat in JFlex.

AST
	Am definit o clasa Ast care va avea ca prim nod(root) un MainNode si va avea un singur copil SequenceNode(nodul curent la inceputul programului).
	In Clasa Leaf se vor declara numele, argumentul, parintele, adancimea si linia fiecarui nod.
	Aceasta clasa va implementa interfata Visitable, iar VarNode, IntNode si BoolNode vor extinde aceasta clasa.
	Clasa Node va extinde clasa Leaf si va avea un ArrayList cu copii acestuia(noduri).
	Celelalte clase(noduri) vor extinde clasa Node.

In JFlex
	Vom folosi o regula VarList pentru a citi variabilele folosite(prima linie) si le vom adauga intr-un Arraylist(ListaVariabile).
	Pentru expresiile aritmetice si boolene, acestea vor fi adaugate intr-un Arralist(Expression).
	Atunci cand se va citi ';' expresia va fi adaugata in AST.
	Mai intai se va trece expresia de la forma infixata la forma prefixata.
	Se vor creea nodurile respective si vor fi adaugate la nodul curent.
	Daca nodul curent are deja 3 copii, atunci se va creea un SequenceNode, ultimii doi copii vor fi adaugati la SequenceNode, iar SequenceNode va
fi adaugat la nodul curent. SequenceNode va deveni noul nod curent.
	Daca in timpul construirii AST-ului am gasit un VarNode care nu apare in ListaVariabile, vom retine eroarea respectiva(UnassignedVar).

	Pentru constructiile de tip "if" si "while", vom crea nodul respectiv, iar acesta va deveni nodul curent.
	Vom adauga conditia la nodul curent.
	Daca am citit "{", atunci se va creea un BlockNode, iar acesta va deveni nodul curent.
	Daca sunt pe BlockNode, atunci trebuie sa verific daca acesta are doi copii.
	Daca da, voi creea un SequenceNode si voi adauga cei doi copii in SequenceNode.
	SequenceNode va fi adaugat la BlockNode.
	Daca am citit "}" vom iesi de pe BlockNode si de pe IfNode sau WhileNode(ne intoarcem inapoi la parintele parintelui).

Printarea Ast-ului
	Avand arborele construit, instantiem un obiect de tipul PrintVisitor ce se ocupa cu printarea Ast-ului in fisierul corespunzator.
	Visitorul de tipul TreeVisitor trece prin nodurile Ast-ului si trasnmite requestul de accept al PrintVisitor pe nodul curent.
	In urma acestuia, in functie de tipul de nod, sunt printate datele corespunzatoare acestuia.

Interpretarea Ast-ului
	Pentru interpreatarea Ast-ului folosim un visitor de tipul InterpretVisitor ce implementeaza interpretarea efectiva a comenzilor si 
scrie rezultatul in fisierul corespunzator.
	Acesta foloseste Lista de variabile citite de pe prima linie din fisier.
	Vom parcurge fiecare nod al Ast-ului pornind din root si in functie de tipul de nod se efectueaza operatia corespunzatoare.
 	De exemplu:
 		Pentru AssignmentNode cautam variabila(copilul 1) in Lista de variabile.
 		Ii setam variabilei din Lista de variabile valoarea respectiva(copilul 2).
 		Folosim functia ReturnValue pentru a returna o valoare sau variabila.
 		Daca variabila este null in Lista de variabile, atunci se va printa in fisier eroarea UnassignedVar si linia unde a fost gasita eroarea.

 		Pentru PlusNode, argumentul acestuia va deveni suma argumentelor celor 2 copii.

 		Pentru DivideNode, argumentul acestuia va deveni rezultatul impartirii argumentului copilului 1 la copilul 2.
 		Daca copilul 2 are argumentul 0, atunci se va printa in fisier eroarea DivideByZero si linia unde a fost gasita eroarea.

 		Pentru AndNode, daca argumentul copilului 1 si al copilului 2 este true, atunci argumentul acestuia va deveni true, altfel va deveni false.

 		Pentru NotNode, daca argumentul copilului este true, atunci argumentul acestuia va deveni false.
 		Daca este false, atunci argumentul va deveni true.

 		Pentru Ifnode, verificam daca conditia are argumentul true(argumentul din BracketNode).
 		Daca da, vom vizita copilul 2 al acestuia(Primul BlockNode).
 		Daca nu, vom vizita copilul 3 al acestuia(Al doilea BlockNode).

 		Pentru WhileNode, cat timp conditia are argumentul true vom parcurge copilul 2 al acestuia.

 		In final folosim functia writeListaVariabile pentru a printa Lista de variabile in fisierul output.
 		Pentru printarea erorii folosim functia writeError.

 Clasa care se ocupa cu printarea in fisier este OutputWriter.
