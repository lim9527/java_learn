package lim95.thread;

/**
 * Created by lim9527 on 8/7 0007.
 */
public class InterruptCheck extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("start");
        while (true){
            if (Thread.currentThread().isInterrupted())
                break;
        }
        System.out.println("while exit");
    }

    public static void main(String[] args){
        Thread thread = new InterruptCheck();
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
