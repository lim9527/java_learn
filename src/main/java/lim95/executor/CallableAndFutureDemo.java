package lim95.executor;



import java.util.concurrent.*;

/**
 * Created by lim9527 on 8/11 0011.
 */
public class CallableAndFutureDemo {

    public static void main(String[] args){

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("future had been invoke");
            }
        }, 5, 10, TimeUnit.SECONDS);

        int count = 0;
        System.out.println(((RunnableScheduledFuture<?>) future).isPeriodic());

        while(true){
            try {
                Thread.sleep(1000);
                count++;
                if (count==10){
                    future.cancel(true);
                    System.out.println("cancel had been used");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("距离future调用还有："+future.getDelay(TimeUnit.SECONDS)+"秒。");
        }


    }

    public static void method6() {
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<Integer> future = (ScheduledFuture<Integer>)scheduledService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("running");
                    }
                }, 5000, 10000, TimeUnit.MILLISECONDS);

        try {
            if (future.get() == null){
                System.out.println("null");
            }else{
                System.out.println("not null");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * FutureTask.get()的使用，延迟Future
     */
    public static void method5() {
        Executor executor = Executors.newCachedThreadPool();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("sleep 5s ready");
                Thread.sleep(5000);
                System.out.println("return result");
                return 1;
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("i am running");
                    System.out.println(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(futureTask);
    }


    /**
     * FutureTask.get()的使用
     */
    public static void method4() {
        Executor executor = Executors.newCachedThreadPool();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("sleep 5s ready");
                Thread.sleep(5000);
                System.out.println("return result");
                return 1;
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("i am running");
                    System.out.println(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(futureTask);
    }

    /**
     * FutureTask的构造函数FutureTask<T>(Runnable, T)的使用
     */
    public static void method3() {
        class Result{
            int result;
        }

        final Result result = new Result();

        FutureTask<Result> futureTask = new FutureTask<Result>(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
                result.result = 100;
            }
        }, result);


        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);


        try {
            System.out.println(futureTask.get().result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * get in callable<V>
     */
    public static void method1() {
        ExecutorService service = Executors.newCachedThreadPool();

        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                int sum = 0;
                for (int i = 0; i < 1000; i++) {
                    sum += i;
                }
                return sum;
            }
        });

        try {
            System.out.println("sum:" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * cancel
     */
    public static void method2() {
        ExecutorService service = Executors.newCachedThreadPool();

        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("thread begin");
                Thread.sleep(2000);
                int sum = 0;
                for (int i = 0; i < 1000; i++) {
                    sum += i;
                }
                System.out.println("thread return");
                return sum;
            }
        });

//        try {
//            System.out.println("sum:"+future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        if (future.isDone()){
            System.out.println("isDone = true");
        }else {
            System.out.println("isDone = false");
        }

        if (future.isCancelled()){
            System.out.println("isCancelled = ture");
        }else{
            System.out.println("isCancelled = false");
            future.cancel(true);
//            future.cancel(false);
        }

        if (future.isCancelled()){
            System.out.println("isCancelled = ture");
        }else{
            System.out.println("isCancelled = false");
        }

//        try {
//            System.out.println("sum:"+future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
