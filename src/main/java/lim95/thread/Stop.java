package lim95.thread;

/**
 * Created by lim9527 on 8/7 0007.
 */
public class Stop extends Thread {
    @Override
    public void run() {
        try {
            while (true)
                ;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Stop();
        thread.start();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }

        thread.stop(new Exception("stop")); // note the stack trace
    }
}
