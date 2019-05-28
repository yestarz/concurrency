package link.yangxin.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * @date 2019/5/28
 */
@Slf4j
public class ThreadExample1 {
    private static void print(){
        log.info(Thread.currentThread().getName());
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(ThreadExample1::print, "thread1");
        Thread thread2 = new Thread(ThreadExample1::print, "thread2");
        Thread thread3 = new Thread(ThreadExample1::print, "thread3");
        thread3.start();
        thread3.join();// 等3结束 再往下执行
        thread1.start();
        thread1.join();// 等1结束 再往下执行
        thread2.start();
        thread2.join();// 等2结束，再往下执行
        System.out.println(TimeUnit.MINUTES.toMillis(1));

    }
}