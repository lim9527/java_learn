package lim95.io.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by lim9527 on 8/15 0015.
 */
public class NioDemo {

    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte)'H').put((byte)'E').put((byte)'L')
                .put((byte)'L').put((byte)'O');

        checkBufferState(buffer);

        byte[] bytes = new byte[3];

        buffer.flip();
        buffer.put((byte)'1').put((byte)'2');
        buffer.flip();

        checkBufferState(buffer);

        buffer.get(bytes);

        System.out.println(new String(bytes));

    }

    public static void writeAndRead() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte)'H').put((byte)'E').put((byte)'L')
                .put((byte)'L').put((byte)'O');

        checkBufferState(buffer);

        buffer.flip();

        byte[] result = new byte[20];

        for (int i = 0; buffer.hasRemaining(); i++) {
            result[i] = buffer.get();
        }

        System.out.println(new String(result));
    }

    public static void checkBufferState(Buffer buffer) {
        System.out.println("___________________________________________________________");
        System.out.print("位置"+buffer.position());
        System.out.print("  上限"+buffer.limit());
        System.out.print("  容量"+buffer.capacity());
        System.out.println("    位置与上限之间的元素"+(int)(buffer.hasRemaining()?buffer.remaining():0));
        System.out.println();
    }


    /**
     * 面向对象
     */
    public static void method1() {
        abstract class A {
           public abstract void method1();
        }

        class B extends A{
            @Override
            public void method1() {
                System.out.println("method1 form B extends A");
            }
        }

        A a = new B();
        a.method1();
    }

    /**
     * 面向对象
     */
    public static void method2() {
        class A {
            public void method1(){
                System.out.println("method1 form A");
            }
        }

        class B extends A{
            @Override
            public void method1() {
                System.out.println("method1 form B extends A");
            }
        }

        A a = new B();
        a.method1();
    }


}
