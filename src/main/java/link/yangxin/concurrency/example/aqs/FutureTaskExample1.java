package link.yangxin.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yangxin
 * @date 2019/6/11
 */
@Slf4j
public class FutureTaskExample1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "done";
            }
        });

        new Thread(futureTask).start();
        log.info("do som in main");
        Thread.sleep(1000);
        String result = futureTask.get();// 如果方法没有执行完毕，这里将会阻塞
        log.info("result" + result);

    }

}