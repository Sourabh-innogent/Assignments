import java.util.*;

public class Performance {

    public static void main(String[] args) {
        int[] testSizes = {10000, 50000, 100000};

        for (int size : testSizes) {
            System.out.println("Testing with " + size + " elements ");

            // ARRAYLIST
            ArrayList<Integer> arrayList = new ArrayList<>();
            long startAL = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.add(i);
            }
            long endAL = System.nanoTime();
            long insertionAL = (endAL - startAL);
            System.out.println("ArrayList Insertion Time: " + insertionAL);

            long startALDel = System.nanoTime();
            while (!arrayList.isEmpty()) {
                arrayList.remove(arrayList.size() - 1);  // remove from end
            }
            long endALDel = System.nanoTime();
            long deletionAL =  (endALDel - startALDel);
            System.out.println("ArrayList Deletion Time: " +deletionAL);

            // LINKED LIST
            LinkedList<Integer> linkedList = new LinkedList<>();
            long startLL = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            long endLL = System.nanoTime();
            long insertionLL =  (endLL - startLL);
            System.out.println("LinkedList Insertion Time: " + insertionLL);

            long startLLDel = System.nanoTime();
            while (!linkedList.isEmpty()) {
                linkedList.removeLast();  // remove from end (O(1) in LinkedList)
            }
            long endLLDel = System.nanoTime();
            long deletionLL =  (endLLDel - startLLDel);
            System.out.println("LinkedList Deletion Time: " + deletionLL);

            System.out.println();
        }
    }
}
