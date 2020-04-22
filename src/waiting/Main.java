package waiting;


import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================TASK 1==================");
        Thread[] threads = new Thread[5];
        long startTime1 = System.currentTimeMillis();
        for (int n=0; n<5; n++) {
            int finalN = n+1;
            threads[n] = new Thread(() -> System.out.println("Thread " + finalN + " is running"));
            threads[n].start();
        }
        for(int i = 0; i < threads.length; i++)
            threads[i].join();
        long endTime1 = System.currentTimeMillis();
        long timeElapsed1 = endTime1 - startTime1;
        System.out.println("all threads finished to run");
        System.out.println("Execution time in milliseconds: " + timeElapsed1);
        System.out.println("==================TASK 2==================");
        Scanner myInput = new Scanner( System.in );
        int N;
        System.out.print( "Enter N: " );
        N = myInput.nextInt();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);
        long startTime2 = System.currentTimeMillis();

        for (int i = 0; i < N; ++i)
            new Thread(new Worker(startSignal, doneSignal)).start();

        System.out.println("before countdown");
        startSignal.countDown();
        System.out.println("after countdown");
        doneSignal.await();
        long endTime2 = System.currentTimeMillis();
        System.out.println("all threads finished to run");
        long timeElapsed2 = endTime2 - startTime2;
        System.out.println("Execution time in milliseconds: " + timeElapsed2);
    }

}
