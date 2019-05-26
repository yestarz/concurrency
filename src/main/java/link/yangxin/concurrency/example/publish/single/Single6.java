package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.ThreadSafe;
import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 饿汉模式的另一种写法
 * 通过静态代码块来初始化值
 * @author yangxin
 * @date 2019/5/26
 */
@ThreadSafe
@Slf4j
public class Single6 {

    private Single6(){
        log.info("对象被{}创建了", Thread.currentThread().getName());
    }

    private static Single6 instance = null;

    static {
        instance = new Single6();
    }

    public static Single6 getInstance(){
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
                Single6.getInstance();
            });
        }
        threadPool.shutdown();
    }


}