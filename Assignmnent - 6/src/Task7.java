import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;


public class Task7 {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter A number for performance Comparison ");

        int totalNumber=input.nextInt();
        List<Integer> numberList = IntStream.range(0,totalNumber).boxed().toList();

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        int size1 = totalNumber / numThreads;
        long eStart = System.currentTimeMillis();

        for (int t = 0; t < numThreads; t++) {
            final int start = t * size1;
            final int end = (t == numThreads - 1) ? totalNumber : start + size1;

            futures.add(executor.submit(() -> {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += numberList.get(i);
                }
                return sum;
            }));
        }

        long sum = 0;
        for (Future<Long> f : futures) sum += f.get();

        long eEnd = System.currentTimeMillis();
        executor.shutdown();

        System.out.println("ExecutorService Sum Time Taken : " + (eEnd - eStart));


        long pStart = System.currentTimeMillis();
        long parallelSum = numberList.parallelStream().mapToLong(i->{
            //try{Thread.sleep(10);}catch(InterruptedException e){}
            return i;
        }).sum();
        long pEnd = System.currentTimeMillis();
        System.out.println("Parallel Sum Time Taken : " + (pEnd - pStart));


    }
}