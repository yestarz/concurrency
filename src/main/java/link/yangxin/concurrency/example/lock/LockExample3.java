package link.yangxin.concurrency.example.lock;

import com.google.common.collect.Maps;
import link.yangxin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Slf4j
public class LockExample3 {

    private final Map<String, Data> map = Maps.newTreeMap();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();// 只有等读锁执行完了以后才能开始写，读多写少的场景会造成饥饿
        try{
            map.put(key, value);
            return value;
        }finally {
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {


    }


    class Data {

    }


}