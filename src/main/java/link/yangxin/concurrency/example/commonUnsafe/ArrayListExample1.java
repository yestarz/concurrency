package link.yangxin.concurrency.example.commonUnsafe;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author yangxin
 * @date 2019/5/28
 */
@Slf4j
@NotThreadSafe
public class ArrayListExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static List<Integer> list = new ArrayList<>();

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
        log.info("size:{}", list.size());
        executorService.shutdown();

    }

    public static  void update(int i){
       list.add(i);
    }

}