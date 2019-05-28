package link.yangxin.concurrency.example.thread;

/**
 * @author yangxin
 * @date 2019/5/28
 */
public class Consumer implements Runnable {

    Person person = null;

    public Consumer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            person.pop();
        }
    }
}