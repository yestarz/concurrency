package link.yangxin.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
public class SemaphoreDemo {

    private static final Semaphore semaphore = new Semaphore(3,true);

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) {

        String[] name= {"李明","王五","张杰","王强","赵二","李四","张三"};
        int[] age = {26, 27, 28, 29, 30, 31, 32};

        for (int i = 0; i < name.length; i++) {
            Thread thread = new InfomationThread(name[i], age[i]);
            threadPool.execute(thread);
        }

    }

    public static class InfomationThread extends Thread {
        private final String name;

        private final int age;

        public InfomationThread(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public void run(){
            try{
                semaphore.acquire();
                log.info("{}，大家好，我是{},今年{}岁，当前时间为：{}",Thread.currentThread().getName(),name,age,System.currentTimeMillis());
                Thread.sleep(1000L);
                log.info("{}要释放许可证了，当前时间为:{}", name, System.currentTimeMillis());
                log.info("当前可用许可证数量为{}", semaphore.availablePermits());
                semaphore.release();
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }

    }


}