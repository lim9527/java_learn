package lim95.thread;

/**
 * Created by lim9527 on 8/7 0007.
 */
public class Suspend extends Thread {
    @Override
    public void run() {
//        synchronized (this) {
//            while (true)
//                ;
            try {
                System.out.println("run start");
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end");
//        }
    }

    public static void main(String[] args) {
        Thread thread = new Suspend();
        thread.start();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }

        thread.suspend();
        System.out.println("resume");

        synchronized (thread) { // dead lock
            System.out.println("got the lock");
            thread.resume();
        }
    }
}
