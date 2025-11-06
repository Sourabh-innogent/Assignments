import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    CountDownLatch latch;
    Worker(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "Thread");
        }
        latch.countDown();
    }
}
public class Task3 {
    public static void main(String[] args) {
    CountDownLatch latch = new CountDownLatch(3);
        Worker worker = new Worker(latch);
        Thread[] thread = new Thread[3];
        String[] names = {"Configuration", "DBConnection", "Cleanup"};
        for(int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(worker , names[i]);
            thread[i].start();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " is Waiting ");
            latch.await();
        } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + " Thread executes Completely ");
    }
}
