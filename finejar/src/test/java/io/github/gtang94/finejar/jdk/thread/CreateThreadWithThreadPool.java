package io.github.gtang94.finejar.jdk.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tanggq
 * @class
 * @description 通过线程池创建线程
 * @date 2021/11/23
 */
public class CreateThreadWithThreadPool implements Runnable {
    @Override
    public void run() {
        System.err.println("通过实现Runnable接口，重写run()方法，并通过线程池启动线程");
    }

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new CreateThreadWithThreadPool());
        executorService.shutdown();
    }
}
