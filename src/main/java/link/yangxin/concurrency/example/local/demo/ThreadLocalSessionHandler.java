package link.yangxin.concurrency.example.local.demo;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * 使用 ThreadLocal 改造后的代码，不再需要在各个方法间传递 Session 对象，并且也非常轻松的保证了每个线程拥有自己独立的实例。
 *
 * @author yangxin
 * @date 2019/5/27
 */
public class ThreadLocalSessionHandler {

    public static ThreadLocal<ThreadLocalSession> threadLocal = new ThreadLocal<>();

    public void createSession() {
        threadLocal.set(new ThreadLocalSession());
    }

    public String getUser() {
        return threadLocal.get().getUser();
    }

    public String getStatus() {
        return threadLocal.get().getStatus();
    }

    public void setStatus(String status) {
        threadLocal.get().setStatus(status);
    }

    public static void main(String[] args) {
        ExecutorService executorService = ThreadPoolUtil.createThreadPool();
        executorService.execute(() -> {
            ThreadLocalSessionHandler sessionHandler = new ThreadLocalSessionHandler();
            sessionHandler.createSession();
            sessionHandler.getStatus();
            sessionHandler.getUser();
            sessionHandler.setStatus("threadLocal");
        });
    }

}