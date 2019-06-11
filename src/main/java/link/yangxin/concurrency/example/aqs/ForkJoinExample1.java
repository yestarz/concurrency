package link.yangxin.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author yangxin
 * @date 2019/6/11
 */
@Slf4j
public class ForkJoinExample1 extends RecursiveTask<Integer> {

    public static final int threshold = 2;

    private int start;

    private int end;

    public ForkJoinExample1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小，则计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinExample1 left = new ForkJoinExample1(start, middle);
            ForkJoinExample1 right = new ForkJoinExample1(middle + 1, end);

            // 执行子任务
            left.fork();
            right.fork();

            // 等待任务执行结束合并其结果
            int leftResult = left.join();
            int rightResult = right.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成一个计算任务，计算1+2+3+...100，假设这是一个巨耗时的任务

        ForkJoinExample1 task = new ForkJoinExample1(1, 100);

        // 执行一个任务
        forkJoinPool.submit(task);

        try{
            log.info("result:{}", task.get());
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

}