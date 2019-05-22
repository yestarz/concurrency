package link.yangxin.concurrency.example.atomic;

import link.yangxin.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    // 必须使用volatile来标记 而且不能是static
    @Getter
    private volatile int count = 100;

    // 第一个参数为当前类的Class 对象，第二个参数是当前类的一个字段名称
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        // 如果example5的count字段的值为100 则更新为120，此时count确实是100，则if条件成立，并将count更新为120
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1 ,count :{}", example5.getCount());
        }
        // 如果example5的count字段的值为100 则更新为120，此时count已经是120了，不满足if条件，则走else语句
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2 ,count :{}", example5.getCount());
        } else {
            log.info("update fail 1 ,count :{}", example5.getCount());
        }

    }

}