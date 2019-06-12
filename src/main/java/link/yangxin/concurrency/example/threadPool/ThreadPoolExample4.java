package link.yangxin.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * @date 2019/6/12
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.schedule(() -> {
            log.warn("exe");
        }, 3, TimeUnit.SECONDS);// 延时3s执行


        executorService.scheduleAtFixedRate(() -> {
            log.info("scheduleAtFixedRate");
        }, 1, 3, TimeUnit.SECONDS);// 延迟1s每隔3s执行1次

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5*1000);

      //  executorService.shutdown();
    }

}