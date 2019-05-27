package link.yangxin.concurrency.example.local.demo;

import lombok.Data;

/**
 * 对于 Java Web 应用而言，Session 保存了很多信息。
 * 很多时候需要通过 Session 获取信息，有些时候又需要修改 Session 的信息。
 * 一方面，需要保证每个线程有自己单独的 Session 实例。
 * 另一方面，由于很多地方都需要操作 Session，存在多方法共享 Session 的需求。
 * 如果不使用 ThreadLocal，可以在每个线程内构建一个 Session实例，并将该实例在多个方法间传递，
 * @author yangxin
 * @date 2019/5/27
 */
@Data
public class ThreadLocalSession {

    private String id;

    private String user;

    private String status;



}