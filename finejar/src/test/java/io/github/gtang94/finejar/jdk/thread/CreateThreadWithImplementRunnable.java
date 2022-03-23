package io.github.gtang94.finejar.jdk.thread;

import org.junit.Test;

/**
 * @author tanggq
 * @class
 * @description 实现Runnable接口实现多线程
 * @date 2021/11/23
 */
public class CreateThreadWithImplementRunnable implements Runnable {

    @Override
    public void run() {
        System.err.println("线程: " + Thread.currentThread().getName()+ ", 通过实现Runnable接口，重写run()方法创建线程");
    }

    // 线程启动
    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(new CreateThreadWithImplementRunnable()).start();
        }

        // 守护线程，即使主线程结束，守护线程也不会结束
//        Thread daemonThread = new Thread(() -> {
//            while (true) {
//                System.err.println("=== daemon ===");
//            }
//        });
//        daemonThread.setDaemon(true);
//        daemonThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
