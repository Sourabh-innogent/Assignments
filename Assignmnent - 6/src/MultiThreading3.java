import java.util.concurrent.*;

public class MultiThreading3 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor2 = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running by " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
            executor2.submit(() -> {
                System.out.println("--->Task " + taskId + " running by " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            });
        }
        CountDownLatch countDownLatch = new CountDownLatch(6);

        executor.shutdown();
        executor2.shutdown();
    }
}
