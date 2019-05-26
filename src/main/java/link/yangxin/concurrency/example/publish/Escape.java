package link.yangxin.concurrency.example.publish;

import link.yangxin.concurrency.annotation.NotThreadSafe;
import link.yangxin.concurrency.annotation.UnRecommend;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出
 * @author yangxin
 * @date 2019/5/26
 */
@UnRecommend
@NotThreadSafe
@Slf4j
public class Escape {

    private int a = 0;

    public Escape (){
        new Inner();
    }

    private class Inner {
        public Inner(){
            log.info("{}", Escape.this.a);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}