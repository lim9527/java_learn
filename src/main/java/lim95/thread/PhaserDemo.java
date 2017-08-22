package lim95.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by lim9527 on 8/14 0014.
 */
public class PhaserDemo {

    public static void main(String[] args){

        /**
         * 本例要实现的功能为：开启3个线程，分别打印字母a,b,c各10次，
         * 然后进入下一阶段打印后面的字母d,e,f各10次，然后再进入
         * 下一阶段.......以此类推，直到整个字母表全部打印完毕。
         * 在此期间主程序进入等待状态，直到3个工作线程全都结束，
         * 主程序才能结束。
         */

        class Task implements Runnable{
            char c;
            Phaser phaser;

            public Task(char c, Phaser phaser) {
                this.c = c;
                this.phaser = phaser;
            }

            @Override
            public void run() {
                while (!phaser.isTerminated()){
                    for (int i = 0; i < 2; i++) {
                        System.out.print(c+" ");
                    }
                    c = (char)(c+3);
                    if (c>'z'){
                        phaser.arriveAndDeregister();
                        break;
                    }else {
                        phaser.arriveAndAwaitAdvance();
                    }
                }
            }
        }

        Phaser phaser = new Phaser(3){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("\n=========================");
                return registeredParties == 1;
            }
        };

        for (int i = 0; i < 3; i++) {
          new Thread(new Task((char)(97+i), phaser)).start();
        }

        phaser.register();
        while (!phaser.isTerminated()){
            phaser.arriveAndAwaitAdvance();
        }

        System.out.println("end");









    }

    /**
     * 有些时候我们希望只有在某些外部条件满足时，才真正开始任务的执行
     */
    public static void method4() {
        class Task implements Runnable {
            //
            private final int id;
            private final Phaser phaser;

            public Task(int id, Phaser phaser) {
                this.id = id;
                this.phaser = phaser;
            }

            @Override
            public void run() {
                System.out.println("in Task.run(), wait");
                phaser.arriveAndAwaitAdvance();
                System.out.println(phaser.getRegisteredParties()+":"+phaser.getArrivedParties());
                System.out.println("in Task.run(), phase: " + phaser.getPhase() + ", id: " + this.id);
            }
        }

        final Phaser phaser = new Phaser(1);
        for(int i = 0; i < 5; i++) {
//            System.out.println(phaser.getRegisteredParties());
//            System.out.println(phaser.getArrivedParties());
//            System.out.println(phaser.getPhase());
            phaser.register();
            System.out.println("starting thread, id: " + i);
            final Thread thread = new Thread(new Task(i, phaser));
            thread.start();
        }

        //
        System.out.println("Press ENTER to continue");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        phaser.arriveAndDeregister();
    }

    /**
     * Phaser模仿CyclicBarrier
     *
     * 加入新内容：重写Phaser的onAdvance方法，类似于CyclicBarrier的Action。
     */
    public static void method1() {
        class PhaserBarrierRunnable implements Runnable{
            Phaser phaser;

            public PhaserBarrierRunnable(Phaser phaser) {
                this.phaser = phaser;
            }

            @Override
            public void run() {
                System.out.println("runnable phaser before ");
                phaser.arriveAndAwaitAdvance();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runnable phaser after ");
            }
        }


        Phaser phaser = new Phaser(5){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("执行Phaser的onAdvance(phase:"+phase+" regParties:"+registeredParties+")");
                return phase == 5;
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(new PhaserBarrierRunnable(phaser)).start();
        }

        System.out.println("main");
    }


    /**
     * Phaser模仿CountDownLatch
     * 未完成
     */
    public static void method2() {
        class PhaserBarrierRunnable implements Runnable{
            Phaser phaser;

            public PhaserBarrierRunnable(Phaser phaser) {
                this.phaser = phaser;
            }

            @Override
            public void run() {
                System.out.println("runnable phaser before ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                phaser.arrive();
                System.out.println("runnable phaser after ");
            }
        }


        Phaser phaser = new Phaser(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new PhaserBarrierRunnable(phaser)).start();
        }


        System.out.println(phaser.awaitAdvance(phaser.getPhase()));

        System.out.println("main");
    }



    public static void method3() {
        class PhaserBarrierRunnable implements Runnable{
            Phaser phaser;

            public PhaserBarrierRunnable(Phaser phaser) {
                this.phaser = phaser;
            }

            @Override
            public void run() {
                System.out.println("runnable phaser before ");
                phaser.arriveAndAwaitAdvance();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runnable phaser after ");
            }
        }


        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("执行Phaser的onAdvance(phase:"+phase+" regParties:"+registeredParties+")");
                return phase == 5;
            }
        };

        phaser.bulkRegister(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new PhaserBarrierRunnable(phaser)).start();
        }




        System.out.println("main");
    }

}
