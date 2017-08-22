package lim95.thread;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lim9527 on 8/7 0007.
 */
public class Wait extends Thread {
    public static Object object = new Object();

    @Override
    public void run() {
        System.out.println("start");
        synchronized (object) { // wait/notify/notifyAll use the same
            // synchronization resource
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); // notify won't throw exception
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Wait();
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }
        synchronized (object) {
            System.out.println("Wait() will release the lock!");
            //thread.notify();
            object.notify();
        }

        ThreadPoolExecutor a;
        AbstractExecutorService abstractExecutorService;
        ThreadPoolExecutor threadPoolExecutor;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
//        threadPoolExecutor.submit(null);
        Executors executors;
    }
}