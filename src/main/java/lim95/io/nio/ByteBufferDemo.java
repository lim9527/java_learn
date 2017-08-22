package lim95.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Created by lim9527 on 8/16 0016.
 */
public class ByteBufferDemo {

    public static void main(String[] args){
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.put(new byte[]{1,2,3,4,5,6,7,8,9});

        byteBuffer.flip();
        byteBuffer.get();
        byteBuffer.get();
        byteBuffer.get();

        byteBuffer.compact();
        System.out.println(byteBuffer.get());

        NioDemo.checkBufferState(byteBuffer);

        byte[] result = byteBuffer.array();
        for (byte b : result) {
            System.out.print(b+" ");
        }
    }

    /**
     * 视图的capacity=（limit-position）/4(IntBuffer)
     */
    public static void method3() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(80);
        byteBuffer.put(new byte[]{1,1,1});
//        byteBuffer.flip();

        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        //intBuffer.put(new int[]{2,2,2,2});

        NioDemo.checkBufferState(byteBuffer);
        NioDemo.checkBufferState(intBuffer);

        byte[] result = byteBuffer.array();
        for (byte b : result) {
            System.out.print(b+" ");
        }
    }

    /**
     * ByteBuffer可以创建多个视图。
     */
    public static void method2() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(80);
        byteBuffer.put(new byte[]{1,1,1,1,1});
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(new int[]{2,2,2,2});
        LongBuffer longBuffer = byteBuffer.asLongBuffer();
        longBuffer.put(new long[]{3,0,3,3,3});

        byteBuffer.put((byte)2);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        charBuffer.put(new char[]{'a', 'b'});

        NioDemo.checkBufferState(byteBuffer);
        NioDemo.checkBufferState(intBuffer);
        NioDemo.checkBufferState(longBuffer);
        NioDemo.checkBufferState(charBuffer);


        byte[] result = byteBuffer.array();
        for (byte b : result) {
            System.out.print(b+" ");
        }
    }

    public static void method1() {
        /**
         * 测试ByteBuffer的视图缓冲区方法。
         */

        //创建ByteBuffer并填入数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        byte[] bytes = {0,0,0,0};
        byteBuffer.put(bytes);
        NioDemo.checkBufferState(byteBuffer);

        byteBuffer.position(1);

        //创建IntBuffer视图
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        NioDemo.checkBufferState(intBuffer);


        intBuffer.put(1);
        intBuffer.put(10);
        NioDemo.checkBufferState(intBuffer);


        //打印ByteBuffer
        byte[] bytes1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
        byteBuffer.put(bytes1);
        intBuffer.put(20);
        intBuffer.mark();
        intBuffer.position(1);
        intBuffer.put(10);
        byteBuffer.flip();
        int count = byteBuffer.remaining();
        for (int i = 0; i < count; i++) {
            System.out.println(byteBuffer.get());
        }


        LongBuffer longBuffer = byteBuffer.asLongBuffer();
        NioDemo.checkBufferState(longBuffer);
        //longBuffer.put(8);


        System.out.println("=============================");

        NioDemo.checkBufferState(byteBuffer);
        NioDemo.checkBufferState(intBuffer);
        //打印IntBuffer
        intBuffer.flip();

        NioDemo.checkBufferState(byteBuffer);
        NioDemo.checkBufferState(intBuffer);
        int count2 = intBuffer.remaining();
        for (int i = 0; i < count2; i++) {
            System.out.println(intBuffer.get());
        }

        NioDemo.checkBufferState(byteBuffer);
        NioDemo.checkBufferState(intBuffer);

        byte[] result = byteBuffer.array();
        for (byte b : result) {
            System.out.print(b+" ");
        }

        longBuffer.flip();
        //System.out.println(longBuffer.get());
    }
}
