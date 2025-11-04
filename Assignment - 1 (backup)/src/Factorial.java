import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

    // Recursive method using BigInteger
    static BigInteger factorial(int x) {
        if (x <= 0)
            return BigInteger.ONE;
        return BigInteger.valueOf(x).multiply(factorial(x - 1));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a number to find its factorial: ");
            System.out.println("(value should be < 10000) ");
            System.out.print("(or enter character to exit) ");
            // Input validation
            if (!sc.hasNextInt()) {
                System.out.println(" Invalid input! Please enter a whole number. exiting...");
                return;
            }

            int n = sc.nextInt();

            if (n < 0) {
                System.out.println(" Factorial is not defined for negative numbers.");
                continue;
            }

            if (n == 0 || n == 1) {
                System.out.println(" Factorial of " + n + " is 1");
                continue;
            }

            BigInteger result = factorial(n);

            System.out.println(" Factorial of " + n + " is: " + result);
        }
    }
}
