// Class that creats new Sieve object which takes input of n numbers with primesTo function and prints all prime numbers up to that number
public class Sieve {
    // LinkedQueues used to find all prime numbers and implement Sieve algorithim
    private LinkedQueue<Integer> numbers;
    private LinkedQueue<Integer> primes;
    private LinkedQueue<Integer> organizer;

    // Constructor initializing the LinkedQueues
    public Sieve() {
        numbers = new LinkedQueue();
        primes = new LinkedQueue();
        organizer = new LinkedQueue<>();
    }

    // Void primesTo function which takes n and used to find all prime numbers up to n and implement Sieve algorithim
    public void primesTo(int n) {

        // Error message if n inputted is less than 2
        if (n < 2) {
            throw new NumberFormatException("Error: Input must be a number greater than 2");
        }

        // For loop to fill up numbers LinkedQueue
        for (int i=2; i<=n; i++) {
            numbers.enqueue(i);
        }

        // Initializes organizer and sets p to the first number in numbers
        int p = numbers.first();
        organizer = numbers;

        // While loop that follows formula of p being less than equal to the square root of n and initializes numbers and organizer for use in the next while loop
        while (p <= Math.sqrt(n)) {
            numbers = organizer;
            organizer = new LinkedQueue<>();
            p = numbers.dequeue();
            primes.enqueue(p);

            // While loop that goes through numbers and elimenates factors of current p
            while (!numbers.isEmpty()) {

                if (numbers.first() % p != 0) {
                    organizer.enqueue(numbers.dequeue());
                }

                else{
                    numbers.dequeue();
                }

            }
        }

        // While loop that empties the rest of organizer as the remaining numbers are prime
        while (!organizer.isEmpty()) {
            primes.enqueue(organizer.dequeue());
        }

        // Prints the prime numbers
        System.out.println("Primes up to " + n + " are: " + primes.toString().substring(1,primes.toString().length() - 1));
    }
}
