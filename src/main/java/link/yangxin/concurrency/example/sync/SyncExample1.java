package link.yangxin.concurrency.example.sync;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * @author yangxin
 * @date 2019/5/23
 */
@Slf4j
public class SyncExample1 {

    // 修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}",j, i);
            }
        }
    }

    // 修饰一个方法，这是一个同步方法
    public synchronized void test2(int j){

            for (int i = 0; i < 10; i++) {
                log.info("test2 - {} {}",j, i);
            }

    }

    public static void main(String[] args) {
        SyncExample1 syncExample1 = new SyncExample1();
        SyncExample1 syncExample2 = new SyncExample1();
        ExecutorService executorService = ThreadPoolUtil.createThreadPool();
        executorService.execute(()->{
            //syncExample1.test1();
            syncExample1.test2(1);
        });
        executorService.execute(()->{
            //syncExample1.test1();
            syncExample2.test2(2);
        });
    }

}