package link.yangxin.concurrency.example.local;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author yangxin
 * @date 2019/5/27
 */
public class TestThreadLocal2 {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = ThreadPoolUtil.createThreadPool();
        threadLocal.set(100);
        pool.execute(() -> {
            System.out.println(threadLocal.get());// null
            threadLocal.set(1000);
            System.out.println(threadLocal.get());// 1000
        });
        Thread.sleep(1000);
        System.out.println(threadLocal.get());//100
    }

}