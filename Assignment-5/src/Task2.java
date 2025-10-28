import java.util.Scanner;

class Consumer extends Thread
{
    Manager a ;
    Consumer(Manager a) {
        this.a = a;
    }
    @Override
    public void run() {
        a.consume();
    }
}

class Producer extends Thread
{
    Manager a ;
    Producer(Manager a) {
        this.a = a;
    }
    public void  run() {
        a.produce();
    }
}
class Manager
{
    int productionCount = 0;
    boolean flag = false;
    int max_production;

    public Manager( int max_production) {
        this.max_production = max_production;
    }

    public synchronized void produce() {
        while (true) {
            while (flag) {
                try {
                    wait(100);
                } catch (InterruptedException ignored) {}
            }

            while (productionCount < max_production) {
                productionCount++;
                System.out.println(Thread.currentThread().getName() + ": Produced product no. " + productionCount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                if (productionCount == max_production) {
                    flag = true;
                    notify();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    public synchronized void consume() {
//        while (true) {
            while (!flag) {
                try {
                    wait(100);
                } catch (InterruptedException ignored) {}
            }

            while(productionCount > 0) {
                System.out.println(" -> "+Thread.currentThread().getName() + ": Consumed product no. " + (max_production - productionCount + 1));
                productionCount--;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
                if(productionCount < (int)(Math.random() * max_production) + 1) {
                    flag = false;
                    try {
                        notify();
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (productionCount == 0) {
                flag = false;
                notify();
            }
        }
    }
//}
public class Task2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter max Production Number");
        int max_production = scan.nextInt();

        Manager a1 =  new Manager(max_production);
        Producer producer = new Producer(a1);
        Consumer consumer = new Consumer(a1);
        producer.setName("Producer");
        consumer.setName("Consumer");

        producer.start();
        consumer.start();

    }
}