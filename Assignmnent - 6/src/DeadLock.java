public class DeadLock {
    public static void main(String[] args) {
        DeadLock a1=new DeadLock();//shared resource -1
        DeadLock b1=new DeadLock();//shared resource -2

        Runnable r1=()->{
            synchronized (a1) {
                System.out.println(Thread.currentThread().getName() + " occupied a1 and waiting for b1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b1) {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(Thread.currentThread().getName() + " is running");
                    }
                }
            }
        };
        Runnable r2=()->{
            synchronized (b1) {
                System.out.println(Thread.currentThread().getName() + " occupied b1 and waiting for a1");
                synchronized (a1) {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(Thread.currentThread().getName() + " is running");
                    }
                }
            }
        };
        Thread threadA=new Thread(r1 , "ThreadA");
        Thread threadB=new Thread(r2 , "ThreadB");
        threadA.start();
        threadB.start();

    }
}
