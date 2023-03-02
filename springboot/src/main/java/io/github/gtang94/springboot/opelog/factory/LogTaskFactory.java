package io.github.gtang94.springboot.opelog.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class LogTaskFactory {

    private static final Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);

    public static TimerTask operateLog(final Long userId, final String operateName, final String className, final String method, final String msg) {

        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("insert into operation log!");
            }
        };
    }
}
