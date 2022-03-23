package io.github.gtang94.finejar.jdk.proxy;

import io.github.gtang94.finejar.jdk.proxy.UserService;

/**
 * @author tanggq
 * @class UserServiceImpl
 * @description
 * @date 2022/2/24
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void say() {
        System.err.println("say");
    }

    @Override
    public void eat() {
        System.err.println("eat");
    }
}
