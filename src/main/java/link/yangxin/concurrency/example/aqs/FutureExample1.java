package link.yangxin.concurrency.example.aqs;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author yangxin
 * @date 2019/6/11
 */
@Slf4j
public class FutureExample1 {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "done";
        }

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executorService = ThreadPoolUtil.createThreadPool();
            Future<String> future = executorService.submit(new MyCallable());
            log.info("do som in main");
            Thread.sleep(1000);
            String result = future.get();// 如果方法没有执行完毕，这里将会阻塞
            log.info("result" + result);

        }

    }



}