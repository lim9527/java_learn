package lim95.collection;

import java.util.*;

/**
 * Created by lim9527 on 8/5 0005.
 */
public class ListIteratorDemo {

    public static void method1(){
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(10,20,30,40,50,60));

        ListIterator iterator = list.listIterator(3);

        System.out.println("nextindex="+iterator.nextIndex());
        System.out.println("previousindex="+iterator.previousIndex());

        System.out.println(iterator.next());
        //System.out.println(iterator.previous());

        System.out.println("nextindex="+iterator.nextIndex());
        System.out.println("previousindex="+iterator.previousIndex());

        System.out.println(iterator.next());
        //System.out.println(iterator.previous());

        System.out.println("nextindex="+iterator.nextIndex());
        System.out.println("previousindex="+iterator.previousIndex());

        System.out.println(iterator.next());
        System.out.println(iterator.previous());

        System.out.println("nextindex="+iterator.nextIndex());
        System.out.println("previousindex="+iterator.previousIndex());



        iterator.set(100);
        System.out.println(list);

        iterator.remove();
        System.out.println(list);

    }


    public static void method2(){
        List<Integer> list1 = new ArrayList<>(Arrays.asList(10,20,30,40,50));
        List<Integer> list2 = new ArrayList<>(list1.size());

        ListIterator iterator1 = list1.listIterator(list1.size());

        for (int i = 0; i < list1.size(); i++) {
           list2.add((Integer) iterator1.previous());
        }

        System.out.println(list2.size());
        System.out.println(list1);
        System.out.println(list2);
    }


    public static void method3(){
        Stack<Integer> stack = new Stack<>();
        stack.add(10);
        stack.add(20);
        stack.add(30);
        stack.add(40);

        System.out.println(stack.search(10));
    }


    public static void method4(){

    }


    public static void main(String[] args){
        ListIteratorDemo.method3();


    }
}
