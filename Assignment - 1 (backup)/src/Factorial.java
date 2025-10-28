public class Factorial {
    static int factorial(int x) {
        return x <= 1 ? x : x * factorial(x - 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int ans = 1;

        System.out.println(factorial(n));

        for(int i = n; i >= 1; --i) {
            ans *= i;
        }

        System.out.println(ans);
    }
}
