package lim95.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lim9527 on 8/4 0004.
 */
public class Test2 {


    public static void main(String[] args){
        Collection<Integer> c = new HashSet<>();
        for (int i=0; i<10; i++){
            c.add(i*10);
        }
        for (int i = 0; i < 10; i++) {
            c.add(i*10);
        }
        for (Integer i: c) {
            System.out.println(i);
        }

    }
}
