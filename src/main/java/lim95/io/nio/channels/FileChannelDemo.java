package lim95.io.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by lim9527 on 8/17 0017.
 */
public class FileChannelDemo {
    public static void main(String[] args) throws Exception{
        method1();


    }

    /**
     * FileChannel的truncate方法。
     * @throws IOException
     */
    public static void method4() throws IOException {
        RandomAccessFile file = new RandomAccessFile("out/production/thread/file/FileChannelTruncateDemo.txt", "rw");
        FileChannel channel = file.getChannel();

        long channelSize = channel.size();
        channel.truncate(channelSize/2);

        channel.close();

        /*效果说明
        *
        * 源文件的内容为：1234567890abcdefghijklmnopqretuvwxyz
        * 共36个字符。
        *
        * 调用truncate方法后，文件的内容为：1234567890abcdefgh
        * 共18个字符。
        *
        * 再次调用truncate方法后，文件的内容为：123456789
        * 共9个字符。
        *
        * */
    }

    /**
     * FileChannel的pisition方法以及size方法。
     * @throws IOException
     */
    public static void method3() throws IOException {
        RandomAccessFile file = new RandomAccessFile("out/production/thread/file/FileChannelPositionDemo.txt", "rw");
        FileChannel channel = file.getChannel();

        System.out.println("原始的position："+channel.position());
        System.out.println("fileChannel的size:" + channel.size());
        System.out.println("randomAccessFile返回的size：" + file.length());


        //大于内容的size也可以。
        System.out.println("移动后的position：" + channel.position(channel.position()+1024).position());
    }

    /**
     * 使用FileChannel写入数据到File中。
     * @throws IOException
     */
    public static void method2() throws IOException {
    /*准备IO流以及FileChannel*/
        RandomAccessFile file = new RandomAccessFile("out/production/thread/file/FileChannelInputDemo.txt", "rw");
        FileChannel channel = file.getChannel();


        /*准备数据以及byteBuffer*/
        String newData = "please input your data to a new file with FileChannel!";

        ByteBuffer buffer = ByteBuffer.allocate(128);//不够长会报错
        buffer.clear();
        buffer.put(newData.getBytes());

        buffer.flip();
        while (buffer.hasRemaining()){
            channel.write(buffer);//覆盖式写入
        }

        /*关闭*/
        channel.close();
    }

    /**
     * 使用FileChannel+ByteBuffer读取文件
     * @throws IOException
     */
    public static void method1() throws IOException {
        RandomAccessFile file = new RandomAccessFile("out/production/thread/file/美女.jpg", "rw");
        FileChannel channel = file.getChannel();

        System.out.println("channel.size():"+channel.size());

        ByteBuffer buffer = ByteBuffer.allocate(1024*10);
        int count = channel.read(buffer);

        while (count != -1){
            System.out.println("count:"+count);
            buffer.clear();
            count = channel.read(buffer);
        }

        channel.close();
    }
}
