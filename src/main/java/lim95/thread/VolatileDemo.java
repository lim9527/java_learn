package lim95.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lim9527 on 8/1 0001.
 */
public class VolatileDemo {

    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increate(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        synchronized (this){
//            this.count++;
//        }
        lock.lock();
        try {
            this.count++;
        }finally {
            lock.unlock();
        }


    }

    public int getCount(){
        return this.count;
    }

    public static void main(String[] args){
        final VolatileDemo demo = new VolatileDemo();

        for (int i=0; i<500; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.increate();
                }
            }).start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("end:"+demo.getCount());
    }
}
