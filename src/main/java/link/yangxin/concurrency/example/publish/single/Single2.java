package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import link.yangxin.concurrency.annotation.ThreadSafe;
import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 饿汉模式
 * 单例的实例在类加载的时候被创建,这是线程安全的
 * 但是这种会造成资源的浪费，因为不管是否使用，都会调用一次构造方法
 * @author yangxin
 * @date 2019/5/26
 */
@ThreadSafe
@Slf4j
public class Single2 {

    private Single2(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    private static Single2 instance = new Single2();

    public static Single2 getInstance(){
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
                Single2.getInstance();
            });
        }
        threadPool.shutdown();
    }


}