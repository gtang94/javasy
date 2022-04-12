package io.github.gtang94.springboot.mybatis.service.impl;

import io.github.gtang94.springboot.mybatis.bean.User;
import io.github.gtang94.springboot.mybatis.bean.Role;
import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import io.github.gtang94.springboot.mybatis.dao.UserDao;
import io.github.gtang94.springboot.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author tanggq
 * @class UserServiceImpl
 * @description
 * @date 2022/4/12
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    public void saveTwoTableNoTransaction(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    @Transactional
    public void saveTwoTableHasTransaction(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    public void testHasTransactionWithPrivate(User user, Role role) {
        callHasTransactionWithPrivate(user, role);
    }

    @Transactional
    public void testHasTransactionWithPrivate2(User user, Role role) {
        callHasTransactionWithPrivate2(user, role);
    }

    @Transactional
    public void testHasTransactionWithFinal(User user, Role role) {
        testHasTransactionWithFinal2(user, role);
    }

    public final void testHasTransactionWithFinal2(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    @Transactional
    private void callHasTransactionWithPrivate(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    private void callHasTransactionWithPrivate2(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }
}
