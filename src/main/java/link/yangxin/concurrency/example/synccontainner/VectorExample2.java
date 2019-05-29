package link.yangxin.concurrency.example.synccontainner;

import link.yangxin.concurrency.annotation.NotThreadSafe;

import java.util.Vector;

/**
 * 同步容器不一定是线程安全的
 * @author yangxin
 * @date 2019/5/29
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });
            thread1.start();
            thread2.start();
        }
    }


}