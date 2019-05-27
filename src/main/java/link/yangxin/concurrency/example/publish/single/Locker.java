package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * 创建对象池，取得对象的标志就是当前的用户ID，用 用户ID从对象池中取得对象作为同步用的锁对象就可以了.
 * 只要保证用户ID一样(参考equals, hashCode)取得得是同一个锁对象(这里用==比较为true)就可以了。
 *
 * @author yangxin
 * @date 2019/4/15
 */
@Slf4j
public class Locker {

    private static volatile Locker instance = null;

    public static Locker getInstance() {
        if (instance == null) {
            synchronized (Locker.class) {
                if (instance == null) {
                    instance = new Locker();
                }
            }
        }
        return instance;
    }

    private Locker() {
        log.info("创建Locker对象");
    }

    private static Map<String, String> lockers = new ConcurrentHashMap<>(); // Value可以是任意得对象

    public String getLocker(String id) {
        if (!lockers.keySet().contains(id)) {
            lockers.put(id, "Lock" + id);
        }
        return lockers.get(id);
    }



}
