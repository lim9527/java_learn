package lim95.threadtest;

/**
 * Created by lim9527 on 8/15 0015.
 */

import java.util.concurrent.Phaser;

/**
 * 有四个线程1、2、3、4。线程1的功能就是输出1，线程2的功能就是输出2，
 * 以此类推.........现在有四个文件ABCD。初始都为空。现要让四个文件呈如下格式：

 A：1 2 3 4 1 2....

 B：2 3 4 1 2 3....

 C：3 4 1 2 3 4....

 D：4 1 2 3 4 1....

 请设计程序。
 */
public class Thread1234FileABCD {

    public static void main(String[] args){

        method1();

    }

    /**
     * 通过修改Phaser的重载函数onAdvance里面的返回条件，phase==n，可以控制线程打印的次数为n+1
     */
    public static void method1() {
        class Task implements Runnable{

            String id;
            int seq;
            StringBuilder[] file;

            Phaser phaser;

            /**
             *
             * @param id 打印的内容
             * @param seq 打印的顺序，0开始
             * @param file
             */
            public Task(String id, int seq, StringBuilder[] file, Phaser phaser) {
                this.id = id;
                this.seq = seq;
                this.file = file;

                this.phaser = phaser;
            }

            @Override
            public void run() {
               while (!phaser.isTerminated()) {
                   file[seq].append(id + " ");
                   seq = (seq +file.length - 1) % file.length;
                   phaser.arriveAndAwaitAdvance();
               }
            }
        }


        Phaser phaser = new Phaser(4){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return phase == 10;
            }
        };

        StringBuilder a = new StringBuilder("");
        StringBuilder b = new StringBuilder("");
        StringBuilder c = new StringBuilder("");
        StringBuilder d = new StringBuilder("");
        StringBuilder[] file = {a, b, c, d};

        new Thread(new Task("1", 0, file, phaser)).start();
        new Thread(new Task("2", 1, file, phaser)).start();
        new Thread(new Task("3", 2, file, phaser)).start();
        new Thread(new Task("4", 3, file, phaser)).start();

        while(!phaser.isTerminated()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }


}
