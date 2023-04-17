import java.util.List;

// Class that constructs a PostfixCalculator object to perform the calculation
public class PostfixCalculator {

    private String infix;
    private LinkedStack<Double> helperStack;
    private Converter inToPost;
    private String postfix;

    private List<String> parsed;

    public PostfixCalculator(String infix) {
        this.infix = infix;
        // Creates the need objects and variables to help with the calculation
        helperStack = new LinkedStack();
        // Converts the infix expression to postfix before calculation begins
        // Printed as a part of the inToPost Method
        inToPost = new Converter(infix);
        postfix = inToPost.toPostFix();
        parsed = ParserHelper.parse(postfix.toCharArray());
    }

    public double calculate() {

        //Values to calculate the postfix expression
        double calcHelper1 = 0;
        double calcHelper2 = 0;

        for (int i=0; i<parsed.size(); i++) {

            //Breaking the postfix statement down into operands and operators
            if (parsed.get(i).equals("^") || parsed.get(i).equals("*") ||
                    parsed.get(i).equals("/") || parsed.get(i).equals("+") || parsed.get(i).equals("-")) {

                calcHelper2 = helperStack.pop();
                calcHelper1 = helperStack.pop();

                if(parsed.get(i).equals("^")) {
                    helperStack.push(Math.pow(calcHelper1 ,calcHelper2));
                }
                if(parsed.get(i).equals("*")) {
                    helperStack.push(calcHelper1 * calcHelper2);
                }
                if(parsed.get(i).equals("/")) {
                    helperStack.push(calcHelper1 / calcHelper2);
                }
                if(parsed.get(i).equals("+")) {
                    helperStack.push(calcHelper1 + calcHelper2);
                }
                if(parsed.get(i).equals("-")) {
                    helperStack.push(calcHelper1 - calcHelper2);
                }
            }
            // Pushes the input if it's not an operator to be used in next loop as a calcHelper Double
            else {
                helperStack.push(Double.parseDouble(parsed.get(i)));
            }
        }
        return helperStack.pop();
    }

}