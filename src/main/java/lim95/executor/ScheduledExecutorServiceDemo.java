package lim95.executor;

import java.util.Timer;
import java.util.concurrent.*;

/**
 * Created by lim9527 on 8/10 0010.
 */
public class ScheduledExecutorServiceDemo {



    public static void main(String[] args){
        method1();

        Timer timer;
    }

    public static void method1() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                System.out.println(count++);
            }
        };

        /*3s后开始计数，每隔1s计数加1.*/
        final ScheduledFuture<?> future = executor.scheduleAtFixedRate(runnable,
                3000, 1000, TimeUnit.MILLISECONDS);

        /*10s后关闭*/
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                System.out.println("cancel()");
                /*shutdown()只是关闭executor*/
                executor.shutdown();
                try {
                    /*等待1min后，终止executor*/
                    executor.awaitTermination(1, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10, TimeUnit.SECONDS);
    }
}
