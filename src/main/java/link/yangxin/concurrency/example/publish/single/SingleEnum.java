package link.yangxin.concurrency.example.publish.single;

import link.yangxin.concurrency.annotation.Recommend;
import link.yangxin.concurrency.annotation.ThreadSafe;

/**
 * 通过枚举来实现单例,最安全的
 * @author yangxin
 * @date 2019/5/26
 */
@ThreadSafe
@Recommend
public class SingleEnum {

    private SingleEnum(){

    }

    public static SingleEnum getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingleEnum singleEnum ;

        // JVM来保证这个方法绝对只调用一次
        Singleton(){
            singleEnum = new SingleEnum();
        }

        public SingleEnum getInstance(){
            return singleEnum;
        }


    }

}