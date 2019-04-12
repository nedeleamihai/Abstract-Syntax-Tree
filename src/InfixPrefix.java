import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class InfixPrefix {
    public boolean isOperator(char c) {
        return (!(c >= 'a' && c <= 'z') &&
                !(c >= '0' && c <= '9') &&
                !(c >= 'A' && c <= 'Z'));
    }

    public int getPriority(char C) {
        if (C == '=') {
            return 1;
        }
        if (C == '&') {
            return 2;
        }
        if (C == '>'){
            return 3;
        }
        if (C == '+') {
            return 4;
        }
        if (C == '/') {
            return 5;
        }
        if (C == '!') {
            return 6;
        }
        return 0;
    }

    public String InfixToPrefixUtil(ArrayList<String> infix) {
        Stack<Character> operators = new Stack<Character>();
        Stack<String> operands = new Stack<String>();

        for (int i = 0; i < infix.size(); i++) {
            //Daca am paranteza deschisa, adaug la operators
            if (infix.get(i).charAt(0) == '(') {
                operators.push('(');
            }
            //Daca am paranteza inchisa, scoatem de pe ambele stive si adaugam rezultatul la stiva operands
            else if (infix.get(i).charAt(0) == ')') {
                String last = ")";
                String first = "";
                while (!operators.empty() && operators.peek() != '(') {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();

                    if(operators.peek() == '('){
                        first = "(";
                    }
                    String tmp = first + " " + op + " " + op2 + " " + op1 + " " + last;

                    last = "";
                    operands.push(tmp);
                }
                //Scot paranteza deschisa de pe stiva
                operators.pop();
            }
            //Daca am operand il adaug pe stiva operands
            else if (!isOperator(infix.get(i).charAt(0))) {
                operands.push(infix.get(i) + "");
            }
            //Daca am un operator, il adaug pe stiva operators dupa ce scot operatorii
            //cu prioritatea mai mare si adaug rezultatul pe stiva operands
            else {
                while (!operators.empty() && getPriority(infix.get(i).charAt(0)) <= getPriority(operators.peek())) {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();

                    String tmp = op + " " + op2 + " " + op1;
                    operands.push(tmp);
                }
                operators.push(infix.get(i).charAt(0));
            }
        }

        //Scoatem operatorii ramasi de pe stiva si ii adaugam la operanzi
        while (!operators.empty()) {
            String op1 = operands.pop();
            String op2 = operands.pop();
            char op = operators.pop();

            String tmp = op + " " + op2 + " " + op1;
            operands.push(tmp);
        }

        return operands.peek();
    }

    public ArrayList<String> InfixToPrefix(ArrayList<String> infix) {

        if(infix.isEmpty()){
            return new ArrayList<String> ();
        }

        ArrayList<Integer> poz = new ArrayList<Integer> ();
        for(int i = 0; i < infix.size(); i++){
            if(infix.get(i).equals("!")){
                poz.add(i);
            }
            if(infix.get(i).equals("true")){
                infix.set(i, "T");
            }
            if(infix.get(i).equals("false")){
                infix.set(i, "F");
            }
        }
        for(int i = poz.size() - 1; i >= 0; i--){
            infix.add(poz.get(i), "A");
        }

        String str = InfixToPrefixUtil(infix);
        if(str.length() == 1){
            str = "( " + str + " )";
        }

        ArrayList<String> prefix = new ArrayList<String> ();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String elem = st.nextToken();
            if(elem.equals("A")){
                continue;
            }else{
                prefix.add(elem);
            }
        }

        return prefix;
    }
}
