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
                arrayList.add(0,i);
            }
            long endAL = System.nanoTime();


            long insertionAL = (endAL - startAL);
            System.out.println("ArrayList Insertion Time: " + insertionAL);



            long startALDel = System.nanoTime();
            while (!arrayList.isEmpty()) {
                arrayList.remove(0);
            }
            long endALDel = System.nanoTime();
            long deletionAL =  (endALDel - startALDel);
            System.out.println("ArrayList Deletion Time: " +deletionAL);



            // LINKED LIST
            LinkedList<Integer> linkedList = new LinkedList<>();
            long startLL = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.addFirst(i);
            }
            long endLL = System.nanoTime();
            long insertionLL =  (endLL - startLL);
            System.out.println("LinkedList Insertion Time: " + insertionLL);

            long startLLDel = System.nanoTime();
            while (!linkedList.isEmpty()) {
                linkedList.removeFirst();
            }
            long endLLDel = System.nanoTime();
            long deletionLL =  (endLLDel - startLLDel);
            System.out.println("LinkedList Deletion Time: " + deletionLL);

            System.out.println("For Case :"+ size + "\n----------");

            if(insertionAL < insertionLL) System.out.println("ArrayList is better for insertion");
            else System.out.println("LinkedList is better for insertion case:" );
            System.out.println();
            if(deletionAL < deletionLL) System.out.println("ArrayList is better for deletion" );
            else System.out.println("LinkedList is better for deletion " );
            System.out.println(" --------------------");
        }
    }
}
