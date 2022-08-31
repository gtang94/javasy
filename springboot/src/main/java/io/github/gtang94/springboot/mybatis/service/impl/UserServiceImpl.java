package io.github.gtang94.springboot.mybatis.service.impl;

import cn.hutool.json.JSONException;
import io.github.gtang94.springboot.mybatis.bean.User;
import io.github.gtang94.springboot.mybatis.bean.Role;
import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import io.github.gtang94.springboot.mybatis.dao.UserDao;
import io.github.gtang94.springboot.mybatis.exception.TransactionErrorException;
import io.github.gtang94.springboot.mybatis.service.RoleService;
import io.github.gtang94.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private TransactionTemplate transactionTemplate;

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
    public void testHasTwoTransactionMutualCall(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        callHasTwoTransactionMutualCall(user, role);
    }

    @Transactional
    public void testHasTransactionMultipleThread(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        new Thread(() -> {
            roleService.save(user, role);
        }).start();
    }

//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.NESTED)
    @Transactional(propagation = Propagation.NEVER)
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.SUPPORTS)
    public void testHasTransactionWithPropagation(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    @Transactional
    public void testHasTransactionCatchException(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        try {
            int i = 0/0;
            roleDao.save(role.getId(), role.getName());
        } catch (Exception e) {
            System.err.println("catch Exception!");
        }

    }

    @Transactional
    public void testHasTransactionThrowOtherException(User user, Role role) throws Exception {
        userDao.save(user.getId(), user.getName());
        try {
            int i = 0/0;
            roleDao.save(role.getId(), role.getName());
        } catch (Exception e) {
            System.err.println("catch Exception!");
            throw new Exception(e);
            // 抛出Exception时事务不生效， 因为Exception异常不属于运行时异常、也不属于错误异常
            // 抛出JSONException时事务会生效，因为它是RuntimeException
            // throw new JSONException(e);
        }
    }

     @Transactional(rollbackFor = TransactionErrorException.class)
    // @Transactional
    public void testHasTransactionThrowCustomException(User user, Role role) throws Exception {
        userDao.save(user.getId(), user.getName());
        try {
            int i = 0/0;
            roleDao.save(role.getId(), role.getName());
        } catch (Exception e) {
            System.err.println("catch Exception!");
             throw new TransactionErrorException();
            // throw new JSONException(e);
            // throw new Exception(e);
//            throw new RuntimeException(e);
        }
    }

    @Transactional(rollbackFor = JSONException.class)
    public void testHasTransactionCatchCustomException(User user, Role role) throws Exception {
        userDao.save(user.getId(), user.getName());
        try {
            callHasTransactionCatchCustomException(user, role);
        } catch (Exception e) {
            System.err.println("catch callHasTransactionCatchCustomException!");
        }
    }

    public void testHasTransactionWithTemplate(User user, Role role) {
        transactionTemplate.execute(status -> {
            userDao.save(user.getId(), user.getName());
            int i = 0/0;
            roleDao.save(role.getId(), role.getName());
            return new Object();
        });
    }

    @Transactional
    public void testHasTransactionWithPrivate2(User user, Role role) {
        callHasTransactionWithPrivate2(user, role);
    }

    public void testHasTransactionWithFinal(User user, Role role) {
        callHasTransactionWithFinal(user, role);
    }

    @Transactional
    public void testHasTransactionWithFinal2(User user, Role role) {
        callHasTransactionWithFinal2(user, role);
    }

    // ============================= call ===================================

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

    @Transactional
    public final void callHasTransactionWithFinal(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    public final void callHasTransactionWithFinal2(User user, Role role) {
        userDao.save(user.getId(), user.getName());
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    @Transactional
    public void callHasTwoTransactionMutualCall(User user, Role role) {
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    public void callHasTransactionCatchCustomException(User user, Role role) throws Exception {
        try {
            int i = 1/0;
            roleDao.save(role.getId(), role.getName());
        } catch (Exception e) {
            throw new JSONException("");
            // throw new Exception("");
        }
    }
}
