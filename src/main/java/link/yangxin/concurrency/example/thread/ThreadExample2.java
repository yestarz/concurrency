package link.yangxin.concurrency.example.thread;

/**
 * @author yangxin
 * @date 2019/5/28
 */
public class ThreadExample2 {

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        Producer producer = new Producer(person);
        Consumer consumer = new Consumer(person);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
        Thread.sleep(2000);
    }

}