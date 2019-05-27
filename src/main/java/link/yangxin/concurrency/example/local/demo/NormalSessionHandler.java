package link.yangxin.concurrency.example.local.demo;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * 该方法是可以实现需求的。但是每个需要使用 Session 的地方，都需要显式传递 Session 对象，方法间耦合度较高。
 * @author yangxin
 * @date 2019/5/27
 */
public class NormalSessionHandler {

    public ThreadLocalSession createSession() {
        return new ThreadLocalSession();
    }

    public String getUser(ThreadLocalSession session) {
        return session.getUser();
    }

    public String getStatus(ThreadLocalSession session) {
        return session.getStatus();
    }

    public void setStatus(ThreadLocalSession session, String status) {
        session.setStatus(status);
    }

    public static void main(String[] args) {
        ExecutorService executorService = ThreadPoolUtil.createThreadPool();
        executorService.execute(() -> {
            NormalSessionHandler sessionHandler = new NormalSessionHandler();
            ThreadLocalSession session = sessionHandler.createSession();
            sessionHandler.getStatus(session);
            sessionHandler.getUser(session);
            sessionHandler.setStatus(session, "normal");
        });
    }


}