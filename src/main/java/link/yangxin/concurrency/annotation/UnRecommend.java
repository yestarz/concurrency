package link.yangxin.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 【不推荐】的写法
 *
 * @author yangxin
 * @date 2019/5/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface UnRecommend {

    String value() default "";

}
