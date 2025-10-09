import java.io.*;
import java.util.concurrent.*;

public class CountLine {
    public static void main(String []ar) throws ExecutionException, InterruptedException{
        ExecutorService executor= Executors.newFixedThreadPool(3);
        Callable<Long> task1 = () -> {
            long count = 0;
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\SourabhYadav\\Desktop\\Innogent Assignments\\Assignmnent - 6\\out\\production\\Assignmnent - 6\\Sample1.txt")))
            {
                while ( reader.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Task 1");
            return count;
        };

        Callable<Long> task2 = () -> {
            long count = 0;
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\SourabhYadav\\Desktop\\Innogent Assignments\\Assignmnent - 6\\out\\production\\Assignmnent - 6\\Sample2.txt")))
            {
                while ( reader.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Task 2");
            return count;
        };

        Callable<Long> task3 = () -> {
            long count = 0;
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\SourabhYadav\\Desktop\\Innogent Assignments\\Assignmnent - 6\\out\\production\\Assignmnent - 6\\Sample3.txt")))
            {
                while ( reader.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Task 3");
            return count;
        };

        Future<Long> future1 = executor.submit(task1);
        Future<Long> future2 = executor.submit(task2);
        Future<Long> future3 = executor.submit(task3);
         System.out.println(future1.get());
         System.out.println(future2.get());
         System.out.println(future3.get());
    }
}
