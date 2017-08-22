package lim95.executor;

import java.util.concurrent.*;

/**
 * Created by lim9527 on 8/10 0010.
 */
public class ExecutorDemo {

    /**
     * 线程执行
     */
    public static void method1(){
        Executor executor = new Executor(){
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("i am runnable 1");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("i am runnable 2");
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("i am runnable 3");
            }
        };

        executor.execute(runnable1);
        executor.execute(runnable2);
        executor.execute(runnable3);
    }

    /**
     * 顺序执行
     */
    public static void method2(){
        Executor executor = new Executor(){
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i am runnable 1");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i am runnable 2");
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i am runnable 3");
            }
        };

        executor.execute(runnable1);
        executor.execute(runnable2);
        executor.execute(runnable3);
    }

    public static void main(String[] args){
        method2();

        ExecutorService service;
        AbstractExecutorService abstractExecutorService;
        ThreadPoolExecutor threadPoolExecutor;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    }
}
