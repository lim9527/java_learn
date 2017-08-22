package lim95.io.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by lim9527 on 8/21 0021.
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        method1();


    }

    public static void method1() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        while (true){
            SocketChannel socketChannel = channel.accept();
        }


//        channel.close();
    }
}
