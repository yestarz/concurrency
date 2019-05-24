package link.yangxin.concurrency.example.atomic;

import link.yangxin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过这种方式可以保证某段代码只执行一次
 *
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean count = new AtomicBoolean(false);

    private static int clientCount = 5000;

    private static int total = 500;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(total);// 同时允许500个线程同时执行

        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test();
                semaphore.release();
            });
        }

    }

    private static void test(){
        if (count.compareAndSet(false, true)) {
            log.info("current value is :{}", count.get());
        }
    }

}