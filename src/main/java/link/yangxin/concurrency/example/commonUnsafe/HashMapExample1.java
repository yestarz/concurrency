package link.yangxin.concurrency.example.commonUnsafe;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yangxin
 * @date 2019/5/28
 */
@Slf4j
@NotThreadSafe
public class HashMapExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);// 信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int j = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                }catch (Exception e){
                    log.error(e.getMessage(), e);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("size:{}", map.size());
        executorService.shutdown();

    }

    public static  void update(int i){
        map.put(i, i);
    }

}