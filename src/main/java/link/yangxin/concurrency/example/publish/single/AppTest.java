package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author yangxin
 * @date 2019/4/15
 */
public class AppTest implements Runnable {

    static ExecutorService pool = ThreadPoolUtil.createThreadPool();
    private static int count = 0;

    private Integer id;

    public AppTest(Integer id) {
        this.id = id;
    }

    public String get() {
        return id + "aaa";
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            pool.execute(new AppTest(1));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);

    }

    @Override
    public void run() {
        Locker instance = Locker.getInstance();
        System.out.println(instance);
        String locker = instance.getLocker(get());
        System.out.println(locker);
        synchronized (locker) {
            count++;
        }
    }
}
