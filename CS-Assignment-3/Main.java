import java.util.Scanner;

// Main class which creates and uses Sieve object
public class Main {
    public static void main (String[] args) {
        Scanner scn = new Scanner(System.in);
        Sieve newSieve = new Sieve();

        // Takes scanner input for what number to use on sieve
        System.out.println("Please enter upper bound");
        int inputP = scn.nextInt();

        // Runs Sieve's primesTo function
        newSieve.primesTo(inputP);
    }
}
