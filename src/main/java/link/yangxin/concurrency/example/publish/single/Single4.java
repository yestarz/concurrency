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
 * 通过双重同步锁单例模式,但是这是线程不安全的，因为有可能出现指令重排的问题,但是概率很小
 * @author yangxin
 * @date 2019/5/26
 */
@NotThreadSafe
@Slf4j
public class Single4 {

    private Single4(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    private static Single4 instance = null;

    public static Single4 getInstance(){
        if (instance == null) { // 双重检测机制 + 同步锁来实现单例，并保证线程安全
            synchronized (Single4.class) {
                if (instance == null)
                instance = new Single4();
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = ThreadPoolUtil.createThreadPool();
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Single4.getInstance();
            });
        }
        threadPool.shutdown();
    }


}