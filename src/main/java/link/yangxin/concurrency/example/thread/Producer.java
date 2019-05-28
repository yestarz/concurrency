package link.yangxin.concurrency.example.thread;

/**
 * @author yangxin
 * @date 2019/5/28
 */
public class Producer implements Runnable {

     Person person = null;

    public Producer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                person.push("tom", 11);
            } else {
                person.push("marry", 21);
            }
        }
    }
}