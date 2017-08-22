package lim95.io.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

/**
 * Created by lim9527 on 8/16 0016.
 */
public class CharsetDemo {

    public static void main(String[] args){
        Charset charset = StandardCharsets.UTF_8;

        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(64);
        charBuffer.put('好');
        charBuffer.put('好');
        charBuffer.put('学');
        charBuffer.put('习');
        charBuffer.put('。');
        charBuffer.flip();


        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = encoder.encode(charBuffer);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        for (;byteBuffer.hasRemaining();){
            System.out.print(byteBuffer.get()+" ");
        }

        byteBuffer.flip();
        try {
            CharBuffer charBuffer2 = decoder.decode(byteBuffer);
            System.out.println("\n"+charBuffer2);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }


    }
}
