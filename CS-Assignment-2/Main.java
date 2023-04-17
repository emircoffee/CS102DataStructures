import java.util.Scanner;

// Class which receives input and provides output for user's desired expression to be calculated.
public class Main {

    public static void main(String[] args) {

        // Scanner object to receive user input
        Scanner scn = new Scanner(System.in);

        System.out.println("Input your desired infix expression:");
        String input = scn.nextLine();
        // PostfixCalculator object to do the calculation
        PostfixCalculator calc = new PostfixCalculator(input);

        System.out.println("Your answer is: " + calc.calculate());

    }
}