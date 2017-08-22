package lim95.io.nio.channels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractInterruptibleChannel;

/**
 * Created by lim9527 on 8/17 0017.
 */
public class ChannelsDemo {

    public static void main(String[] args) throws Exception{

        method2();

    }

    /**
     * FileChannel.transferFrom().
     * 功能类似于复制。
     *
     * 方法的输入参数position表示从position处开始向目标文件写入数据，
     * count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，
     * 则所传输的字节数要小于请求的字节数。
     *
     * 此外要注意，在SoketChannel的实现中，SocketChannel只会传输
     * 此刻准备好的数据（可能不足count字节）。因此，SocketChannel
     * 可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
     */
    public static void method2() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("out/production/thread/file/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("out/production/thread/file/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;//position指toFile.txt中的位置。从0开始。覆盖原数据。
        long count = fromChannel.size()*10;//count指要传输的长度。

        /*下面两者的功能相似*/
//        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.transferTo(position, count, toChannel);
    }

    /**
     * 读取文件
     */
    public static void method1() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("out/production/thread/file/channeltest.txt","rw");
            System.out.println("找到文件");
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        }

        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = -1;
        try {
            bytesRead = fileChannel.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (bytesRead != -1){
            System.out.println("");
            System.out.println("Read----------->"+bytesRead);
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.print(byteBuffer.get());
            }

            byteBuffer.clear();
            try {
                bytesRead = fileChannel.read(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
