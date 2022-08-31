package io.github.gtang94.finejar.design.singleton;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 饿汉
public class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 3000, TimeUnit.SECONDS, new LinkedBlockingDeque<>(500));

        for (int i = 0; i < 500; i ++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    HungrySingleton singleton = HungrySingleton.getSingleton();
                    System.err.println(singleton);
                }
            });
        }

        executor.shutdown();
    }
}
