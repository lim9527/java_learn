package lim95.io.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by lim9527 on 8/21 0021.
 */
public class DatagramChannelDemo {

    public static void main(String[] args) throws IOException {
        method3();

    }

    /**
     * 连接到特定的地址。要注意udp是无连接的，所以该操作并非创建一个真正的连接，而是将该
     * DatagramChannel锁住，让它只能从特定的地址收发数据。
     *
     * @throws IOException
     */
    public static void method3() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
//        channel.socket().bind(new InetSocketAddress(80));
        channel.connect(new InetSocketAddress("www.baidu.com", 80));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int bytesRead = channel.read(byteBuffer);
        int bytesWrite = channel.write(byteBuffer);
    }

    /**
     * DatagramChannel的发送数据。
     * @throws IOException
     */
    public static void method2() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(80));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.put("hello".getBytes());


        byteBuffer.flip();
        int bytesSent = channel.send(byteBuffer, new InetSocketAddress("www.baidu.com", 80));
    }

    /**
     * DatagramChannel获取数据
     * @throws IOException
     */
    public static void method1() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(80));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            byteBuffer.clear();
            channel.receive(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println(byteBuffer.getChar());
            }
        }
    }
}
