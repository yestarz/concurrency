package link.yangxin.concurrency.example.atomic;

import link.yangxin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2); // 2 是0的时候等于2 默认值是0，则count = 2
        count.compareAndSet(0, 1); // no 是0的时候等于1 ，现在count = 2 不满足条件
        count.compareAndSet(1, 3); // no 是1的时候等于3 ，现在count还是2 不满足条件
        count.compareAndSet(2, 4); // 4 是2的时候等于4 现在count是2，满足条件，将count修改为4
        count.compareAndSet(3, 5); // no 是3的时候等于5 现在count是4，不满足条件
        log.info("count:{}",count.get()); // 4

    }

}