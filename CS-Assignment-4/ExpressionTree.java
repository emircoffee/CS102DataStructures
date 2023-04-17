import java.util.List;
import java.lang.Math;

// Formated similarly to Calculator class from postfix calculator but creates expression tree object
// and has methods to convert, and run through the tree in pre,in, and postorder 
public class ExpressionTree {
    private String infix;
    private Converter inToPost;
    private String postfix;

    private List<String> parsed;

    private LinkedStack<Node> treeStack;
    public Node root;

    public ExpressionTree(String infix) {
        this.infix = infix;
        // Creates the need objects and variables to help with the calculation
        // Converts the infix expression to postfix before calculation begins
        // Printed as a part of the inToPost Method
        inToPost = new Converter(infix);
        postfix = inToPost.toPostFix();
        parsed = ParserHelper.parse(postfix.toCharArray());

        treeStack = new LinkedStack();
        root = null;
    }

  
    // New Methods for Tree Based Conversion are Here:
    public Node convert() {

      for (int i=0; i<parsed.size(); i++) {
  
       //Breaking the postfix statement down into operands and operators
        if (parsed.get(i).equals("^") || parsed.get(i).equals("*") ||
            parsed.get(i).equals("/") || parsed.get(i).equals("+") || parsed.get(i).equals("-"))                     {

             if(parsed.get(i).equals("^")) {
                  Node temp = treeStack.pop();
                  Node node = new Node(parsed.get(i), treeStack.pop(), temp);
                  treeStack.push(node);
              }
              if(parsed.get(i).equals("*")) {
                  Node temp = treeStack.pop();
                  Node node = new Node(parsed.get(i), treeStack.pop(), temp);
                  treeStack.push(node);
              }
              if(parsed.get(i).equals("/")) {
                  Node temp = treeStack.pop();
                  Node node = new Node(parsed.get(i), treeStack.pop(), temp);
                  treeStack.push(node);
              }
               if(parsed.get(i).equals("+")) {
                  Node temp = treeStack.pop();
                  Node node = new Node(parsed.get(i), treeStack.pop(), temp);
                  treeStack.push(node);
               }
               if(parsed.get(i).equals("-")) {
                  Node temp = treeStack.pop();
                  Node node = new Node(parsed.get(i), treeStack.pop(), temp);
                  treeStack.push(node);
               }
            }
            // Pushes the input if it's not an operator to be used in next loop as a operand
            else {
              Node node = new Node(parsed.get(i));
              treeStack.push(node);
            }
        }
      root = treeStack.pop();
      return root;
        
    }

  //pre,in, and postfix methods

  public void prefix()  //used as a driver for real infix method
	{
		prefix(root);
	}
    public void prefix(Node tree) {
      if(tree != null) {
			  System.out.print(tree);
			  prefix(tree.leftChild);
			  prefix(tree.rightChild);
		  }
    }

  public void infix()  //used as a driver for real infix method
	{
		infix(root);
	}
    public void infix(Node tree) {
      if(tree.leftChild != null)  //assume all operators are binary operands
		  {
			System.out.print("("); 
			infix(tree.leftChild);
		  }
		  System.out.print(tree);
		  if(tree.leftChild != null)  //assume all operators are binary operands
		  {
			infix(tree.rightChild);
			System.out.print(")");
		  }
    }

    public void postfix()  //used as a driver for real postfix method
	{
		postfix(root);
	}
    public void postfix(Node tree) {
      if(tree != null) {
			postfix(tree.leftChild);
			postfix(tree.rightChild);
			System.out.print(tree);
		  }
    }
}