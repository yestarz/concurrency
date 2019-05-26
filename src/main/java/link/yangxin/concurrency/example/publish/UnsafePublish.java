package link.yangxin.concurrency.example.publish;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author yangxin
 * @date 2019/5/26
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = new String[]{"a", "b", "c"}; // 定义一个数组

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();// 发布一个对象
        log.info("{}", Arrays.toString(unsafePublish.getStates())); // a,b,c

        unsafePublish.getStates()[0] = "d"; // 尝试对这个发布的对象的属性进行修改，发现可以修改成功，那么我们在多线程环境下，也无法避免这个属性是否会被其他线程所修改，所以这是线程不安全的
        log.info("{}", Arrays.toString(unsafePublish.getStates())); // d,b,c
    }

}