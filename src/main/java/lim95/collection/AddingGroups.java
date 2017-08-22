package lim95.collection;

import java.util.*;

/**
 * Created by lim9527 on 8/4 0004.
 */
public class AddingGroups {

    public static void main(String[] args){
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4));

        Integer[] moreInts = {6,7,8,9};
        collection.addAll(Arrays.asList(moreInts));

        Collections.addAll(collection, 11,12,13,14);

        for (Integer i: collection) {
            System.out.println(i);
        }

        List<Integer> integers = Arrays.<Integer>asList(1,2);

    }
}
