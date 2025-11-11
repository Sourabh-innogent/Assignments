import java.util.Scanner;
import java.util.concurrent.Semaphore;

class ParkingLot extends Thread
{
    Semaphore semaphore;
    String carName;
    ParkingLot(Semaphore semaphore, String carName)
    {
        this.semaphore = semaphore;
        this.carName = carName;
    }
    public void run()
    {
        System.out.println(" !!! "+carName + " waiting");
        try {
            semaphore.acquire();
            System.out.println("<<--"+carName + " acquired parking");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("-->>"+carName + " released parking");
            semaphore.release();
        }
    }

}
public class CarParking {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter size of a parking lot");
            int size = input.nextInt();
            if (size <= 0) {
                System.out.println("Parking lot size must be positive!");
                return;
            }
            Semaphore semaphore = new Semaphore(size);
            for (int i = 0; i < size * 2; i++) {
                new ParkingLot(semaphore, "Car " + (i + 1)).start();
            }
        }
        finally {
            input.close();
        }

    }
}
