import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    CountDownLatch latch;
    Worker(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "Thread");
        }
        latch.countDown();
    }
}
public class CountDownLatchDemo {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(3);

        String[] taskNames = {"Configuration", "DBConnection", "Cleanup"};

        for (String name : taskNames) {
            new Thread(new Worker(latch), name).start();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " is Waiting ");
            latch.await();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("All Thread executes Completely ");
    }
}
