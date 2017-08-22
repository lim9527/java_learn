package lim95.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by lim9527 on 8/6 0006.
 */
public class LatchDemo {

    public static void state1(){
        System.out.println("state_1 is ready.");
    }

    public static void state2(){
        System.out.println("state_2 is ready.");
    }

    public static void state3(){
        System.out.println("state_3 is ready.");
    }

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args){
        new Thread(){
            @Override
            public void run() {
                super.run();
                state1();
                latch.countDown();
                System.out.println("state 1 is end");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                state2();
                latch.countDown();
                System.out.println("state 2 is end");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                state3();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();

                System.out.println("state 3 is end");
            }
        }.start();

        try {
            latch.await(10L, TimeUnit.SECONDS);
        } catch (Exception e){

        }
        System.out.println("that is ok");

    }
}
