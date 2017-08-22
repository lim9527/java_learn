package lim95.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lim9527 on 7/31 0031.
 */
public class AtomicVariable {
    public static AtomicInteger count = new AtomicInteger(10_000_000);

    public static Integer getCount(){
        return count.get();
    }

    public static void main(String[] args){

        Thread a = new Thread(new MyThteadAtomic(1));
        Thread b = new Thread(new MyThteadAtomic(2));

        a.start();
        b.start();


        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getCount());
    }


}

class MyThteadAtomic implements Runnable{
    /**
     * 1:加法  2：减法
     */
    private int type;

    public MyThteadAtomic(int type) {
        this.type = type;
    }

    @Override
    public void run() {
        if (type == 1) {
            for (int i = 0; i < 1_000_000; i++)
                AtomicVariable.count.incrementAndGet();
        }
        else {
            for (int i = 0; i < 1_000_000; i++)
                AtomicVariable.count.decrementAndGet();
        }
    }
}