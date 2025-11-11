import java.io.*;
import java.util.concurrent.*;

public class CountLine {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            Callable<Long> task1 = () -> countLines("src/Sample1.txt", "Task 1");
            Callable<Long> task2 = () -> countLines("src/Sample2.txt", "Task 2");
            Callable<Long> task3 = () -> countLines("src/Sample3.txt", "Task 3");

            Future<Long> future1 = executor.submit(task1);
            Future<Long> future2 = executor.submit(task2);
            Future<Long> future3 = executor.submit(task3);

            System.out.println("File 1 lines: " + future1.get());
            System.out.println("File 2 lines: " + future2.get());
            System.out.println("File 3 lines: " + future3.get());

        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error executing tasks: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    static long countLines(String filePath, String taskName) {
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " " + taskName + " completed");
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            return -1;
        }
        return count;
    }
}
