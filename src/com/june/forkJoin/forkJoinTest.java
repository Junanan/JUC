package com.june.forkJoin;

import java.util.concurrent.RecursiveTask;

public class forkJoinTest extends RecursiveTask<Long> {
    private Long start;//起始值
    private Long end;//结束值
    public static final Long critical = 10000L;//临界值

    public forkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end - start;
        if (length <= critical) {
            //如果拆分完毕就相加
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            //没有拆分完毕就开始拆分
            Long middle = (end + start)>>1;
            forkJoinTest right = new forkJoinTest(start, middle);
            right.fork();//拆分，并压入线程队列
            forkJoinTest left = new forkJoinTest(middle+1,end);
            left.fork();//拆分，并压入线程队列
            //合并
            return right.join()+left.join();
        }
    }
}
