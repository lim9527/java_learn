package lim95.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by lim9527 on 8/6 0006.
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception{
        CyclicBarrier barrier = new CyclicBarrier(4, new BarrierActionThread());
        for (int i = 0; i < 3; i++) {
            new BarrierTestThread(barrier).start();
        }
        System.out.println("wait for barrier together");
        barrier.await(5, TimeUnit.MILLISECONDS);
        System.out.println("end");


    }

    static class BarrierActionThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("优先执行！");
        }
    }

    static class BarrierTestThread extends Thread{
        CyclicBarrier barrier;

        public BarrierTestThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            super.run();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程"+Thread.currentThread().getName()+" ok");

            try {
                barrier.await(20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("wait for barrier");
        }
    }
}
