import java.util.List;

// Converter class which will take given infix expression and convert it to a postfix expression
// Updated to work with usecases it didn't previously work with in assignment 2
public class Converter {

    private String infix;
    private List<String> parsed;
    private String output;
    private LinkedStack<String> helperStack;
    private ParserHelper parser;


    public Converter(String infix) {

        this.infix = infix;
        //String list object to handle output of parser-helper's parse method
        parsed = parser.parse(infix.toCharArray());
        //System.out.println(parsed);
        output = "";
        //A string LinkedStack object to help with converting the infix expression
        helperStack = new LinkedStack();
    }




    //String method that handles the main task of converting infix expression to postfix
    public String toPostFix() {

        for (int i=0;i<parsed.size();i++) {

            // if statement checks if string character is not an operand
            if (parsed.get(i).equals("(") || parsed.get(i).equals(")") ||
                    parsed.get(i).equals("^") || parsed.get(i).equals("*") || parsed.get(i).equals("/") ||
                    parsed.get(i).equals("+") || parsed.get(i).equals("-")) {

                // Calls precedenceCheck to handle operators
                precedenceCheck(parsed.get(i));
            }
            else {
                output = output + parsed.get(i) + " ";
            }
        }
        while (!helperStack.isEmpty()) {
            output = output + helperStack.pop() + " ";
        }
        System.out.println("Your infix expression converted to postfix is: " + output);
        return output;
    }

    // Helper method meant to check which operators have precedence and handle them in relation to the stack correctly
    private void precedenceCheck(String token) {
        if(token.equals("(")) {
            helperStack.push(token);
        }

        if(token.equals(")")) {
            while (!helperStack.top().equals("(")) {
                output = output + helperStack.pop() + " ";

            }
            helperStack.pop();
        }

        if(token.equals("^") || token.equals("*") || token.equals("/") ||
                token.equals("+") || token.equals("-")) {
            if(!helperStack.isEmpty()) {
                if(precedence(token)) {
                    helperStack.push(token);
                }
                else {
                    while(!precedence(token) && !helperStack.isEmpty()) {
                        output = output + helperStack.pop() + " ";
                    }
                    if(helperStack.isEmpty()) {
                        helperStack.push(token);
                    }
                }
            }
            else {
                helperStack.push(token);
            }
        }
    }

    // Helper boolean method which compares stack to current token in the for loop to see if the string in the stack has precedence
    // Follows PEMDAS for precedence

    private boolean precedence(String token) {
        if(!helperStack.isEmpty()) {
            if (helperStack.top().equals("(")) {

                return true;
            }

            if (token.equals("^")) {

                return true;
            }

            if (token.equals("*") || token.equals("/")) {
                if (helperStack.top().equals("(")  || helperStack.top().equals("-") || helperStack.top().equals("+") || token.equals("*") || token.equals("/") || token.equals("^")) {
                    return true;
                } else {
                    return false;
                }
            }

            if (token.equals("+") || token.equals("-")) {
                if (helperStack.top().equals("(")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}
