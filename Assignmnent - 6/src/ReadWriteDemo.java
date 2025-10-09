import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Main
{
    int count=1;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    public void increment()
                                                                                                                                                                                {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+" increase the count");
            count++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
    public int getCount()
    {
        try {
            readLock.lock();
            return count;
        }
        finally {
            readLock.unlock();
        }
    }
}
public class ReadWriteDemo {
    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();

        Runnable read = new Runnable() {
            public void run() {
                for(int i=0;i<10;i++)
                    {
                    System.out.println(Thread.currentThread().getName()+" : "+m.getCount());
                   }
            }
        };
        Runnable write = new Runnable() {
            public void run() {
                for(int i=0;i<10;i++)
                    {
                            m.increment();
                    }
            }
        };
        Thread writer1 = new Thread(write,"Writer- 1");
        Thread t2 = new Thread(read , "Reader - 1");
        Thread t3 = new Thread(read , "Reader - 2");
        writer1.start();
        t2.start();
        t3.start();
        writer1.join();
        System.out.println(Thread.currentThread().getName()+" : "+m.getCount());
    }
}
