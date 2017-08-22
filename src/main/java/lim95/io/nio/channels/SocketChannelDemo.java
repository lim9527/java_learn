package lim95.io.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by lim9527 on 8/20 0020.
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        method2();


    }

    /**
     * SocketChannel连接到服务器并获取服务器返回的数据。
     *
     * 没有成功。
     * @throws IOException
     */
    public static void method2() throws IOException {
    /*SocketChannel read方法返回0值
        http://blog.csdn.net/expleeve/article/details/39577943
     */

        SocketChannel channel = SocketChannel.open();
        /*  this is very importment.  */
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("http://www.baidu.com", 80));


        /*确保SocketChannel上的connect已经完成。*/
        while (!channel.finishConnect()){
            System.out.println("等待非阻塞连接建立...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /*虽然此时已经建立了channel到服务器上的连接，但是channel.read()极有可能返回0.
        * 这是因为我们没有向服务器发送请求，所以也就得不到服务器的响应。
        */
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put("get".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        buffer.clear();
        int result = channel.read(buffer);

        while (result != -1){
            buffer.flip();

            if (result == 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (buffer.hasRemaining()){
                System.out.println(buffer.get());
            }

            buffer.clear();
            result = channel.read(buffer);
        }

        channel.close();
        System.out.println("end");
    }

    /**
     * 打开SocketChannel
     * @throws IOException
     */
    public static void method1() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("www.baidu.com", 80));
        channel.close();
    }
}
