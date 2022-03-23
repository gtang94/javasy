package io.github.gtang94.finejar.jdk.thread;

import org.junit.Test;

/**
 * @author tanggq
 * @class
 * @description 通过继承Thread类实现多线程
 * @date 2021/11/23
 */
public class CreateThreadWithThreadClass extends Thread {

    @Override
    public void run() {
        System.err.println("通过继承Thread类，重写run()方法创建线程");
    }

    // 线程启动
    @Test
    public void testThreadStart() {
        new CreateThreadWithThreadClass().start();
    }

    @Test
    public void test1() {
        new Thread(()-> System.out.println("runnable")){
            @Override
            public void run() {
                System.out.println("Thread run");
            }
        }.start();
    }

}
