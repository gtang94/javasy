package io.github.gtang94.finejar.jdk.thread;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import sun.nio.ch.ThreadPool;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tanggq
 * @class
 * @description 通过Executors工厂类创建线程
 * @date 2021/11/23
 */
public class CreateThreadWithExecutor {

    /**
     * 创建固定大小的线程池，超出的线程会在队列中等待
     *
     * 适用场景： 任务量已知，相对耗时的任务
     * 缺点：
     *      1. 大小固定，不好扩展
     *      2. 允许的请求队列长度为Integer.MAX_VALUE，可能导致堆积请求，导致内存溢出
     */
    @Test
    public void testFixedThreadPool() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<String>> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            Future<String> task = executorService.submit(
                    () -> {
                        System.err.println(StrUtil.format("线程 {} -> success", Thread.currentThread().getName()));
                        TimeUnit.SECONDS.sleep(1);
                        return "success";
                    }
            );
            list.add(task);
        }
        executorService.shutdown();

        for (Future<String> task: list) {
            try {
                String taskResult = task.get(60, TimeUnit.SECONDS);
                if ("success".equals(taskResult)) {
                    System.err.println(StrUtil.format("result ({})", taskResult));
                } else {
                    System.err.println(StrUtil.format("result (fail)"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个可缓存的线程池，最大线程数Integer.MAX_VALUE，空闲线程60s后回收
     *
     * 适用场景：执行时间短、 异步任务比较多的情况
     * 缺点：
     *      1. 可能会创建大量线程，造成内存溢出
     */
    @Test
    public void testCachedThreadPool() {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newCachedThreadPool(
                (r) -> {
                    return new Thread(r, "t" + atomicInteger.incrementAndGet());
                }
        );

        for (int i = 0; i < 100; i++) {
            executorService.submit(
                    () -> {
                        System.err.println(StrUtil.format("线程 {} -> 执行", Thread.currentThread().getName()));
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        executorService.shutdown();
    }

    /**
     * 创建一个单线程的线程池，保证线程的顺序执行
     *
     * 适用场景： 多个任务顺序执行
     * 缺点：
     *      1. 不适合并发场景
     *      2. 允许的请求队列长度为Integer.MAX_VALUE，可能导致堆积请求，导致内存溢出
     */
    @Test
    public void testSingleThreadPool() {

        // FIXME: 执行后没有输入，不知道原因
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {

            if (i == 55) {
                executorService.submit(
                        () -> {
                            System.err.println(StrUtil.format("线程 {} -> 异常", Thread.currentThread().getName()));
                            int r = 19/0;
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
            } else {
                executorService.submit(
                        () -> {
                            System.err.println(StrUtil.format("线程 {} -> 正常", Thread.currentThread().getName()));
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        }
        executorService.shutdown();
    }

    /**
     * 创建一个固定大小的线程池，可以定时或定期的执行任务
     *
     * 适用场景：固定周期的的重复任务
     * 缺点：
     *      1. 可能会创建大量线程，造成内存溢出
     */
    @Test
    public void testScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // 创建并执行一个周期性任务
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> {
                    System.err.println(StrUtil.format("线程 {} -> 进行", Thread.currentThread().getName()));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                2, 1, TimeUnit.SECONDS
        );

        // 创建并执行一个周期性任务，该任务在指定初始化延迟后首次启动，之后再指定周期内再启动
        scheduledExecutorService.scheduleAtFixedRate(
                () -> {
                    System.err.println(StrUtil.format("线程 {} -> 进行", Thread.currentThread().getName()));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 2, 1, TimeUnit.SECONDS
        );

        scheduledExecutorService.shutdown();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // ========================== 推荐方式 ==========================


    /**
     * 提交任务后的执行顺序：
     *      1. 运行线程 < corePoolSize，先创建核心线程数corePoolSize
     *      2. corePoolSize < 运行线程 < maximumPoolSize 时，将任务放到缓存队列workQueue中
     *      3. corePoolSize < 运行线程 < maximumPoolSize 并且 workQueue满 时，创建新线程
     */
    @Test
    public void testThreadPoolExecutor() {

        // 自定义创建线程池的参数

        // 线程池中核心线程的数量
        int corePoolSize = 10;
        // 线程池中最大可容量的线程数量
        int maximumPoolSize = 15;
        // 非核心线程的生命周期
        long keepAliveTime = 100;
        // 单位
        TimeUnit unit= TimeUnit.SECONDS;
        // 线程池排队策略： FIFO、 指定缓存队列为： 5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(200);
        // 线程池工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        // 线程饱和策略
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        // 根据上面的参数，自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, abortPolicy);

        for (int i = 0; i < 100; i++) {
            CustomTask task = new CustomTask();
            threadPoolExecutor.execute(task);

            System.err.println(
                    StrUtil.format("线程池中线程数量：{}\n队列中等待执行的任务数量：{}\n已执行完的任务数量：{}\n", threadPoolExecutor.getPoolSize(), threadPoolExecutor.getQueue().size(), threadPoolExecutor.getCompletedTaskCount())
            );
        }

        threadPoolExecutor.shutdown();

    }




    public static class CustomTask implements Runnable {

        @Override
        public void run() {
            System.err.println(StrUtil.format("线程 {} => 正在执行", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(StrUtil.format("线程 {} => 执行完毕", Thread.currentThread().getName()));
        }
    }
}
