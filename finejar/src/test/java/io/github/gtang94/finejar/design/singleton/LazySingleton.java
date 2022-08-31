package io.github.gtang94.finejar.design.singleton;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 懒汉
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 3000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 1000; i++) {
            executor.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            LazySingleton singleton = LazySingleton.getInstance();
                            System.err.println(singleton);
                        }
                    }
            );
        }

        executor.shutdown();
    }

}
