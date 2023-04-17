import java.util.Scanner;

// Class which receives input and provides output for user's desired expression to be converted to a tree.
public class Main {

    public static void main(String[] args) {

        // Scanner object to receive user input
        Scanner scn = new Scanner(System.in);

      while(true){
        System.out.println("Input your expression:");
        String input = scn.nextLine();
        // PostfixCalculator object to do the calculation
        ExpressionTree tree = new ExpressionTree(input);

        // Converts postfix expression to tree in a while loop and
        // prints pre,in, and postfix expressions by running through the tree in
        // pre,in, and postorder respectively
        tree.convert();
        System.out.print("Prefix: ");
        tree.prefix();
        System.out.println("");
        System.out.print("Infix: ");
        tree.infix();
        System.out.println("");
        System.out.print("Postfix: ");
        tree.postfix();
        System.out.println("");
        System.out.println("");
      }

    }
}