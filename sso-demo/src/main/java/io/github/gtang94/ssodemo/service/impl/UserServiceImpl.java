package io.github.gtang94.ssodemo.service.impl;

import io.github.gtang94.ssodemo.entity.SysUser;
import io.github.gtang94.ssodemo.repository.SysUserRepository;
import io.github.gtang94.ssodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
