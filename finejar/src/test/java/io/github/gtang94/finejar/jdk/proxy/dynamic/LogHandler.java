package io.github.gtang94.finejar.jdk.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tanggq
 * @class LogHandler
 * @description
 * @date 2022/2/24
 **/
public class LogHandler implements InvocationHandler {

    Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(target, args);
        after();
        return null;
    }

    public void before() {
        System.err.println("============= before =================");
    }

    public void after() {
        System.err.println("============= after =================");
    }
}
