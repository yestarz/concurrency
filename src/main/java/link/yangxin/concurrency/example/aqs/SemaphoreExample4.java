package link.yangxin.concurrency.example.aqs;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * @date 2019/6/3
 */
@Slf4j
public class SemaphoreExample4 {

    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = ThreadPoolUtil.createThreadPool();
        final Semaphore semaphore = new Semaphore(3);// 允许20个线程同时执行
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(()->{
                try {
                    // 尝试获取一个许可，最多等5s，如果获取到了则开始执行,
                    if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {

                        test(threadNum);
                        semaphore.release();// 释放一个许可
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            });
        }
        //log.info("finished");
        service.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        TimeUnit.SECONDS.sleep(1);

    }


}