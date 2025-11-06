import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

class Even extends Thread
{
    A a = null;
    Even(A a) {
        this.a = a;
    }
    @Override
    public void run() {
        a.printNumber();
    }
}

class Odd extends Thread
{
    A a = null;
    Odd(A a) {
        this.a = a;
    }
    public void  run() {
            a.printNumber();
    }
}
class A
{
    boolean flag = false;
    int count = 1;
    int limit = 0;
        A(){}
        A(int limit){
            this.limit = limit;
        }

    public synchronized void printNumber()
    {
        while(count <= limit) {
            if(flag) {
                System.out.println(Thread.currentThread().getName() + " -> " + count++);
                flag = false;
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                flag = true;
                notify();
            }
        }
        notify();
    }
}
public class Task1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num = 0;

        while(num == 0) {
            try {
                System.out.println("Enter a Number up to you want Even or Odd");
                num = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please! Enter a Number\n");
                sc.nextLine();
            }
        }

        A a1 =  new A(num);
        Even even = new Even(a1);
        Odd odd = new Odd(a1);
        even.setName("EvenThread");
        odd.setName("OddThread");
        odd.start();
        even.start();
    }
}