package lim95.io.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by lim9527 on 8/21 0021.
 */
public class PipeDemo {

    public static void main(String[] args) throws IOException {
        method1();
    }

    /**
     * @throws IOException
     */
    public static void method1() throws IOException {
        Pipe pipe = Pipe.open();

        /*向管道写数据*/
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.put("hello".getBytes());

        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            sinkChannel.write(byteBuffer);
        }

        /*向管道读数据*/
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        byteBuffer1.clear();
        int bytesRead = sourceChannel.read(byteBuffer1);
        while (bytesRead != -1){
            byteBuffer1.flip();
            while (byteBuffer1.hasRemaining()){
                System.out.println((char)byteBuffer1.get());
            }
            byteBuffer1.clear();
            bytesRead = sourceChannel.read(byteBuffer1);
        }

        System.out.println("end");
    }
}
