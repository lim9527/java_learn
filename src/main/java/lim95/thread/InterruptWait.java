package lim95.thread;

/**
 * Created by lim9527 on 8/7 0007.
 */
public class InterruptWait extends Thread {

    public static Object object = new Object();

    @Override
    public void run() {
        super.run();
        System.out.println("start");
        synchronized (object){
            try {
                object.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
//                e.printStackTrace();
            }
        }
    }

    /*
     * 当一个线程调用了wait()方法而处于等待中的状态，被另一个线程使用中断唤醒，
     * 于是抛出InterruptedException异常，同时，清除中断标志。
     *
     * 所以我们通常会在捕获该异常的地方重新设置中断，便于后续的逻辑通过检查中断状态
     * 了解该线程是如何结束的。
     *
     */


    public static void main(String[] args){
        Thread thread = new InterruptWait();
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
