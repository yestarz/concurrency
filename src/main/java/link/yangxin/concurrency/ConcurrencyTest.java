package link.yangxin.concurrency;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    // 请求总数
    public static int clientTotal = 10000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);// 信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
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
        log.info("count:{}",count);
        executorService.shutdown();

    }

    public static void add(){
        count++;
    }

}