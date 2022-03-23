package io.github.gtang94.finejar.jdk.proxy.statics;

import io.github.gtang94.finejar.jdk.proxy.UserService;

/**
 * @author tanggq
 * @class UserProxy
 * @description
 * @date 2022/2/24
 **/
public class UserServiceProxy implements UserService {

    private UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }


    @Override
    public void say() {
        before();
        target.say();
        after();
    }

    @Override
    public void eat() {
        before();
        target.eat();
        after();
    }

    public void before() {
        System.err.println("============= before =================");
    }

    public void after() {
        System.err.println("============= after =================");
    }
}
