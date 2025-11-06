import java.util.Scanner;

class Consumer extends Thread {
    Manager a;
    Consumer(Manager a) {
        this.a = a;
    }
    @Override
    public void run() {
        a.consume();
    }
}

class Producer extends Thread {
    Manager a;
    Producer(Manager a) {
        this.a = a;
    }
    public void run() {
        a.produce();
    }
}

class Manager {
    int productionCount = 0;
    boolean flag = false;
    int max_production;
    int cycleCount = 0;

    public Manager(int max_production) {
        this.max_production = max_production;
    }

    public synchronized void produce() {
        while (cycleCount < 5) {
            while (flag) {
                try { wait(); } catch (InterruptedException ignored) {}
            }

            productionCount = 0;
            while (productionCount < max_production) {
                productionCount++;
                System.out.println(Thread.currentThread().getName() + ": Produced product no. " + productionCount);
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }

            flag = true;
            notify();
            cycleCount++;
        }
        System.out.println("Production stopped after 5 cycles.");
    }

    public synchronized void consume() {
        while (cycleCount < 5) {
            while (!flag) {
                try { wait(); } catch (InterruptedException ignored) {}
            }

            int consumed = 0;
            while (productionCount > 0) {
                System.out.println(" -> " + Thread.currentThread().getName() + ": Consumed product no. " + (max_production - productionCount + 1));
                productionCount--;
                consumed++;
                try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            }

            flag = false;
            notify();
        }
        System.out.println("Consumption stopped after 5 cycles.");
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter max Production Number:");
        int max_production = scan.nextInt();

        Manager a1 = new Manager(max_production);
        Producer producer = new Producer(a1);
        Consumer consumer = new Consumer(a1);

        producer.setName("Producer");
        consumer.setName("Consumer");

        producer.start();
        consumer.start();
    }
}
