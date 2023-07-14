package io.github.gtang94.ssodemo.service;


import io.github.gtang94.ssodemo.entity.SysUser;

public interface UserService {

    SysUser getByUsername(String username);
}
