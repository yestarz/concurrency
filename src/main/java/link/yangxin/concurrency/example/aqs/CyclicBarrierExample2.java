package link.yangxin.concurrency.example.aqs;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author yangxin
 * @date 2019/6/3
 */

@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = ThreadPoolUtil.createThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    private static void race(int num) throws Exception{
        Thread.sleep(1000);//模拟其他操作
        log.info("{} is ready",num);
        try {
            cyclicBarrier.await(2000L, TimeUnit.MILLISECONDS);// 当前线程ok了
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn(e.getMessage());
        }
        log.info("{} is continue", num);
    }


}