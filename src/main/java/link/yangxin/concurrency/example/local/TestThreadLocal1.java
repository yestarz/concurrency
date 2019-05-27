package link.yangxin.concurrency.example.local;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author yangxin
 * @date 2019/5/27
 */
public class TestThreadLocal1 {

    private static ThreadLocal<String> threadLocalA = new ThreadLocal<>();

    private static ThreadLocal<String> threadLocalB = new ThreadLocal<>();

    public static void setA(String value){
        threadLocalA.set(value);
    }

    public  static String getA() {
        return threadLocalA.get();
    }

    public static void clearA(){
        threadLocalA.remove();
    }

    public static void setB(String value){
        threadLocalB.set(value);
    }

    public static String getB() {
        return threadLocalB.get();
    }

    public static void clearB(){
        threadLocalB.remove();
    }

    public static void main(String[] args) {
        ExecutorService pool = ThreadPoolUtil.createThreadPool();
        pool.execute(()->{
            setA("a1");
            //System.out.println("thread1:" + getA());
            //clearA();

            setB("b1");
            //System.out.println("thread1:" + getB());
            //clearB();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.execute(()->{
            //setA("a2");
            System.out.println("thread2:" + getA());
            //clearA();

            //setB("b2");
            System.out.println("thread2:" + getB());
            //clearB();
        });

    }

}