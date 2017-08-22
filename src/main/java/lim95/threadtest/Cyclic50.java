package lim95.threadtest;

import java.util.concurrent.Phaser;

/**
 * Created by lim9527 on 8/15 0015.
 */

/**
 * 子线程循环 10 次，接着主线程循环 5 次，接着又回到子线程循环 10 次，
 * 接着再回到主线程又循环 5 次，如此循环50次，试写出代码.
 */
public class Cyclic50 {

    public static void main(String[] args){
        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    synchronized (lock) {
                        for (int j = 0; j < 10; j++) {
                            System.out.println("子线程在循环，这是第 " + i + " 阶段，第 " + j + " 次循环");
                        }
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 5; j++) {
                    System.out.println("主线程在循环，这是第 " + i + " 阶段，第 " + j + " 次循环=====");
                }
                lock.notify();
            }
        }

    }
}
