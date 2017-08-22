package lim95.io.nio.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * Created by lim9527 on 8/21 0021.
 */
public class PathDemo {
    public static void main(String[] args){
        method1();

    }

    /**
     * 获取Path对象的4种方法。
     */
    public static void method1() {
        Path path = null;

//        path = Paths.get("out/production/thread/file/PathTest.txt");

//        path = Paths.get("out", "production","thread","file","PathTest.txt");

        path = FileSystems.getDefault().getPath("out/production","thread","file","PathTest.txt");


//        File file = new File("out/production/thread/file/PathTest.txt");
//        path = file.toPath();


        PrintPath(path);
    }

    /**
     * 打印文件内容
     * @param path
     */
    public static void PrintPath(Path path) {
        try {
            if (Files.isReadable(path)){
                BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset());
                String line = "";
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }else {
                System.err.println("cannot readable.");
            }
        } catch (IOException e) {
            System.err.println("error charset.");
        }
    }


}
