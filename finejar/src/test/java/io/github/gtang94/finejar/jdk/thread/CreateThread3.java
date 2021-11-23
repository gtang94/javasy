package io.github.gtang94.finejar.jdk.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author tanggq
 * @class
 * @description
 * @date 2021/11/23
 */
public class CreateThread3 implements Callable {

    @Override
    public String call() throws Exception {
        return "通过实现Callable接口，重写call()方法创建线程";
    }

    @Test
    public void test() throws Exception {
        FutureTask<String> task = new FutureTask<>(new CreateThread3());
        new Thread(task).start();
        System.err.println(task.get());
    }
}
