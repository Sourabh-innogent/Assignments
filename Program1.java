import java.util.*;

public class Program1 {

    public static void main(String[] args) {
        int[] testSizes = {10000, 50000, 100000};

        for (int size : testSizes) {
            System.out.println("==== Testing with " + size + " elements ====");

            // ARRAYLIST
            ArrayList<Integer> arrayList = new ArrayList<>();
            long startAL = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.add(i);
            }
            long endAL = System.nanoTime();
            System.out.println("ArrayList Insertion Time: " + (endAL - startAL) );

            long startALDel = System.nanoTime();
            while (!arrayList.isEmpty()) {
                arrayList.remove(arrayList.size() - 1);  // remove from end
            }
            long endALDel = System.nanoTime();
            System.out.println("ArrayList Deletion Time: " + (endALDel - startALDel) );

            // LINKED LIST
            LinkedList<Integer> linkedList = new LinkedList<>();
            long startLL = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            long endLL = System.nanoTime();
            System.out.println("LinkedList Insertion Time: " + (endLL - startLL) );

            long startLLDel = System.nanoTime();
            while (!linkedList.isEmpty()) {
                linkedList.removeLast();  // remove from end (O(1) in LinkedList)
            }
            long endLLDel = System.nanoTime();
            System.out.println("LinkedList Deletion Time: " + (endLLDel - startLLDel) );

            System.out.println();
        }
    }
}
