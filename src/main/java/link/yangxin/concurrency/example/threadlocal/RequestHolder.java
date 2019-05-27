package link.yangxin.concurrency.example.threadlocal;

/**
 * @author yangxin
 * @date 2019/5/27
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();// 一定要remove，不然会造成内存泄漏
    }
}