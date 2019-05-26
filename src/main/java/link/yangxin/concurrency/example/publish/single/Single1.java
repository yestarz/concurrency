package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候被创建
 * @author yangxin
 * @date 2019/5/26
 */
@NotThreadSafe
@Slf4j
public class Single1 {

    private Single1(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    private static Single1 instance = null;

    // 这是线程不安全的，在多线程环境中，22 - 23行可能被多个线程同时执行，则可能多个线程会被实例化多次，导致单例对象不一致
    public static Single1 getInstance(){
        if (instance == null) {
            instance = new Single1();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = ThreadPoolUtil.createThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Single1.getInstance();
            });
        }
        threadPool.shutdown();
    }


}