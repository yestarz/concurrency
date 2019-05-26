package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import link.yangxin.concurrency.annotation.ThreadSafe;
import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候被创建,通过使用synchronized关键字来实现线程安全。
 * 通过双重同步锁单例模式, 通过volatile避免CPU指令重排序
 * @author yangxin
 * @date 2019/5/26
 */
@ThreadSafe
@Slf4j
public class Single5 {

    private Single5(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    // volatile + 双重检测机制 禁止指令重排
    private static volatile Single5 instance = null;

    public static Single5 getInstance(){
        if (instance == null) { // 双重检测机制 + 同步锁来实现单例，并保证线程安全
            synchronized (Single5.class) {
                if (instance == null)
                instance = new Single5();
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
                Single5.getInstance();
            });
        }
        threadPool.shutdown();
    }


}