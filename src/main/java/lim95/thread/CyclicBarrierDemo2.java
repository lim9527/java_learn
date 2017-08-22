package lim95.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lim9527 on 8/14 0014.
 */
public class CyclicBarrierDemo2 {


    public static void main(String[] args){

        method1();
    }

    /**
     * 测试CyclicBarrier的action.
     *
     * 测试结果：最后一个进入barrier的线程，在进入barrier之后立即执行atcion，不管它是否还有没执行的代码。
     */
    public static void method1() {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("由最后一个进入barrier的线程执行。" + Thread.currentThread().getName());
            }
        });


        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread "+Thread.currentThread().getName()+" begin.");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread "+Thread.currentThread().getName()+" end.");
                }
            }).start();
        }
    }
}
