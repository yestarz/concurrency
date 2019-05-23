package link.yangxin.concurrency.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxin
 * @date 2019/5/23
 */
public class ThreadPoolUtil {

    public static ExecutorService createThreadPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService;
    }

}