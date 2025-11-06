import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockPrevention{
    public static void main(String[] args) {
        DeadLockPrevention a1=new DeadLockPrevention();//shared resource -1
        DeadLockPrevention b1=new DeadLockPrevention();//shared resource -2

        Lock lock1=new ReentrantLock();
        Lock lock2=new ReentrantLock();
        Runnable r1=()->{
            try {
                if(lock1.tryLock(1000,TimeUnit.NANOSECONDS)){
                    System.out.println(Thread.currentThread().getName() + " occupied lock1");
                    if(lock2.tryLock(100,TimeUnit.MILLISECONDS) ){
                        System.out.println(Thread.currentThread().getName() + " occupied lock1");
                        lock2.unlock();
                        }
                    else lock1.unlock();
                    }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable r2=()->{
            try {
                if(lock2.tryLock(1000, TimeUnit.NANOSECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " occupied lock2");
                    if(lock1.tryLock(10000,TimeUnit.MILLISECONDS)) //MilliSecond can work here
                        {
                        System.out.println(Thread.currentThread().getName() + " occupied lock1");
                        }
                    }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread threadA=new Thread(r1 , "ThreadA");
        Thread threadB=new Thread(r2 , "ThreadB");
        threadA.start();
        threadB.start();

    }
}
