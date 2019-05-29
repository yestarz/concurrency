package link.yangxin.concurrency.example.synccontainner;

import link.yangxin.concurrency.annotation.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * 同步容器不一定是线程安全的
 * @author yangxin
 * @date 2019/5/29
 */
public class VectorExample3 {

    // Exception in thread "main" java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v) {
        for (Integer integer : v) {
            if (integer.equals(3)) {
                v.remove(integer);
            }
        }
    }

    //Exception in thread "main" java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
                v.remove(next);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void main(String[] args) {
       Vector<Integer> vector = new Vector<>();
        for (int i = 1; i < 4; i++) {
            vector.add(i);
        }
        //test1(vector);
        test2(vector);
        //test3(vector);

    }


}