package lim95.threadtest;

/**
 * Created by lim9527 on 8/15 0015.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按
 * ABC的顺序显示；如：ABCABC….依次递推。
 */
public class PrintABC {

    private static int[] seq = {1, 0, 0};


    public static void main(String[] args){


        method2();





    }

    /**
     * 使用一个int[] seq和int id来控制线程的执行顺序，再使用synchronized(seq)
     * 来保证各个线程对seq的可见性与原子性。
     *
     * 比较满意的方法。
     */
    public static void method2() {
        class Task implements Runnable{

            String name;
            int id;
            int[] seq;

            public Task(String name, int id, int[] seq) {
                this.name = name;
                this.id = id;
                this.seq = seq;
            }

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    while (true) {
                        synchronized (seq) {
                            if (seq[id] == 1) {
                                System.out.print(name + " ");
                                int nextId = (id + 1) % seq.length;
                                seq[id] = 0;
                                seq[nextId] = 1;
                                break;
                            }
                        }
                    }


                }

            }
        }

        //1表示执行，0表示休息


        new Thread(new Task("A", 0, seq)).start();
        new Thread(new Task("B", 1, seq)).start();
        new Thread(new Task("C", 2, seq)).start();
    }

    /**
     * 使用计划线程池实现。
     *
     * 0分
     */
    public static void method1() {
        class Task implements Runnable{

            String name;

            public Task(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                System.out.print(name+" ");
            }
        }

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Task("A"),10,30, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new Task("B"),20,30, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new Task("C"),30,30, TimeUnit.MILLISECONDS);
    }
}
