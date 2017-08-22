package lim95.thread;

/**
 * Created by lim9527 on 7/31 0031.
 */
public class RaceConditions {
    public static int num=1_000_000;

    public static void main(String[] args){

        Thread a = new Thread(new MyThtead(1));
        Thread b = new Thread(new MyThtead(2));

        a.start();
        b.start();


        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }

}


class MyThtead implements Runnable{
    /**
     * 1:加法  2：减法
     */
    private int type;

    public MyThtead(int type) {
        this.type = type;
    }

    @Override
    public void run() {
        if (type == 1) {
            for (int i = 0; i < 100_000; i++)
                RaceConditions.num++;
        }
        else {
            for (int i = 0; i < 100_000; i++)
                RaceConditions.num--;
        }
    }
}