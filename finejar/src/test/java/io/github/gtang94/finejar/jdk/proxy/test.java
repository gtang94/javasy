package io.github.gtang94.finejar.jdk.proxy;

import io.github.gtang94.finejar.jdk.proxy.dynamic.LogHandler;
import io.github.gtang94.finejar.jdk.proxy.statics.UserServiceProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author tanggq
 * @class test
 * @description
 * @date 2021/5/11
 **/
public class test {

    @Test
    public void testStaticsProxy() {
        UserService userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(userService);

        proxy.say();

        proxy.eat();
    }

    @Test
    public void testDynamicProxy() {
        UserService userService = new UserServiceImpl();

        ClassLoader classLoader = userService.getClass().getClassLoader();
        Class<?>[] interfaces = userService.getClass().getInterfaces();

        LogHandler logHandler = new LogHandler(userService);
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);

        proxy.say();
        proxy.eat();
    }
}
