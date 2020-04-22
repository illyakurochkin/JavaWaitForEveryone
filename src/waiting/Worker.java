package waiting;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private static int counter = 0;
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        counter++;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {}
    }

    void doWork() {
        System.out.println("thread "+ counter + " is running");
    }
}