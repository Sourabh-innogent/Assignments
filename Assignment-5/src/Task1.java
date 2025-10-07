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
    int count = 1;
    boolean flag = false;

    public synchronized void printNumber()
    {
        while(count <= 20) {
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
        A a1 =  new A();
        Even even = new Even(a1);
        Odd odd = new Odd(a1);
        even.setName("EvenThread");
        odd.setName("OddThread");
        odd.start();
        even.start();
    }
}