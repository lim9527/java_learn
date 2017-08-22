package lim95.io.nio;

import java.nio.ByteOrder;

/**
 * Created by lim9527 on 8/16 0016.
 */
public class ByteOrderDemo {

    public static void main(String[] args){
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        System.out.println(byteOrder.toString());//Little-endian
    }
}
