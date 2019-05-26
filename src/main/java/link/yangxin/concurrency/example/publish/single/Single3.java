package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import link.yangxin.concurrency.annotation.ThreadSafe;
import link.yangxin.concurrency.annotation.UnRecommend;
import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候被创建,通过使用synchronized关键字来实现线程安全。
 * 但是这是不推荐使用的，因为synchronized是方法级别，每次访问都会加锁，会影响性能
 * @author yangxin
 * @date 2019/5/26
 */
@ThreadSafe
@Slf4j
@UnRecommend
public class Single3 {

    private Single3(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    private static Single3 instance = null;

    public static synchronized Single3 getInstance(){
        if (instance == null) {
            instance = new Single3();
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
                Single3.getInstance();
            });
        }
        threadPool.shutdown();
    }


}