package link.yangxin.concurrency.example.local;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * https://my.oschina.net/u/1047712/blog/150131
 *
 * @author yangxin
 * @date 2019/5/27
 */
@Slf4j
public class TestThreadLocal3 {
    private static Index index = new Index();
    private static ThreadLocal<Index> threadLocal = new ThreadLocal<Index>() {
        @Override
        protected Index initialValue() {
            return new Index();// 注意这里，每个线程必须是不同的实例，否则变量计算将不准确
        }
    };

    public static void main(String[] args) {
        ExecutorService pool = ThreadPoolUtil.createThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                Index index = threadLocal.get();
                for (int j = 0; j < 1000; j++) {
                    index.increase();
                }
                log.info("{}:{}", Thread.currentThread().getName(), index.idx);
            });

        }
    }


    private static class Index {
        private int idx;

        public void increase() {
            idx++;
        }
    }

}