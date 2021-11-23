package io.github.gtang94.finejar.jdk.thread;

import org.junit.Test;

/**
 * @author tanggq
 * @class
 * @description
 * @date 2021/11/23
 */
public class CreateThread2 implements Runnable {

    @Override
    public void run() {
        System.err.println("通过实现Runnable接口，重写run()方法创建线程");
    }

    @Test
    public void test() {
        new Thread(new CreateThread2()).start();
    }
}
