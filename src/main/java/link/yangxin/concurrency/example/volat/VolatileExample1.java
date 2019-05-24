package link.yangxin.concurrency.example.volat;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * volatile 关键字具有可见性。能一定程度的防止重排序
 * 在每次读volatile变量的时候，会从主内存中读取最新的值，在写volatile变量的时候，会将值及时刷新到主内存中
 *
 * @author yangxin
 * @date 2019/5/24
 */
@Slf4j
public class VolatileExample1 {

    private static volatile boolean isInit = false;

    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        log.info("===========初始化中===========");
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        isInit = true;// 刷新到主内存中。
    }

    private static void sleep() {
        log.info("===========还没有初始化完成==========");
    }

    public static void main(String[] args) {
        ExecutorService threadPool = ThreadPoolUtil.createThreadPool();
        threadPool.execute(() -> {
            // 只要还没有初始化完成，都执行此代码
            /*
             * isInit 变量使用volatile来修饰，当前线程读这个值会从主内存中读取最新的值。
             * 如果没有用volatile来修饰，这个值不是最新的，当另一个线程执行的时候，没有把值刷新到最新，这里永远都是false，导致这个循环是一个死循环。
             */
            while (!isInit) {
                sleep();
            }
            log.info("初始化完成！list.size:{}", list.size());
        });
        threadPool.execute(() -> {
            try {
                init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.info("执行完毕");

    }

}