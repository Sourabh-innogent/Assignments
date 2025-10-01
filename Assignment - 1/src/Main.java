public class Main {
    static int factorial(int x) // --> method area
    {
        if(x <= 1) return x; //-> Stack
        return x * factorial(x-1);//-> all method calls in Stack
     }

    public static void main(String[] args) {
        int n = 5;
        int ans = 1; // -> all local variable are in stack
        for(int i = n; i >= 1; i--)
        {
            ans = ans * i;
        }
        System.out.println(ans);// System.out -> Heap
    }
}