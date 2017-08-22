package lim95.thread;

import java.util.concurrent.*;

/**
 * Created by lim9527 on 8/6 0006.
 */
public class CallableAndFutrueDemo {

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future = service.submit(task);
        service.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread main is running!");
//        future.cancel(false);

        try {
            System.out.println("The result is " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}

class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable Task is running!");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("Callable Task is end!");
        return sum;
    }
}
