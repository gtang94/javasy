package io.github.gtang94.springboot.mybatis.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.github.gtang94.springboot.mybatis.bean.Person;
import io.github.gtang94.springboot.mybatis.bean.Role;
import io.github.gtang94.springboot.mybatis.bean.User;
import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import io.github.gtang94.springboot.mybatis.service.UserService;
import io.github.gtang94.springboot.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * @author tanggq
 * @class TransactionalController
 * @description
 * @date 2022/4/12
 **/
@RestController
@RequestMapping("/transaction")
public class TransactionalController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 条件：不加事务
     * 现象：user表可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/no-transaction")
    public String testNoTransaction() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.saveTwoTableNoTransaction(user, role);
        return "OK";
    }

    /**
     * 条件：加事务
     * 现象：user表不可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction")
    public String testHasTransaction() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.saveTwoTableHasTransaction(user, role);
        return "OK";
    }

    /**
     * 条件：调用非事务方法， 非事务方法再调用事务方法
     * 现象：user表可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-with-private")
    public String testHasTransactionWithPrivate() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPrivate(user, role);
        return "OK";
    }

    /**
     * 条件：调用事务方法，事务方法 再调用 非事务方法
     * 现象：user表不可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-with-private2")
    public String testHasTransactionWithPrivate2() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPrivate2(user, role);
        return "OK";
    }

    /**
     * 条件：调用非事务方法，非事务方法再调用 final事务方法
     * 现象：user表不可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-with-final")
    public String testHasTransactionWithFinal() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithFinal(user, role);
        return "OK";
    }

    /**
     * 条件：调用事务方法，事务方法再调用 final非事务方法
     * 现象：user表不可insert数据，role insert不了数据
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-with-final2")
    public String testHasTransactionWithFinal2() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithFinal2(user, role);
        return "OK";
    }

    /**
     * 条件：调用事务方法，非事务方法再调用 final事务方法
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-two-transaction-mutual-call")
    public String testHasTwoTransactionMutualCall() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTwoTransactionMutualCall(user, role);
        return "OK";
    }

    /**
     * 条件：调用事务方法，事务方法再调用 多线程中的事务方法
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-multiple-thread")
    public String testHasTransactionMultipleThread() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionMultipleThread(user, role);
        return "OK";
    }

    /**
     * 条件：指定错误传播性
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-with-propagation")
    public String testHasTransactionWithPropagation() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPropagation(user, role);
        return "OK";
    }

    /**
     * 条件：自己捕获异常，且未抛出异常
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-catch-exception")
    public String testHasTransactionCatchException() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionCatchException(user, role);
        return "OK";
    }

    /**
     * 条件：手动抛出其他类异常（非RuntimeException和ErrorException）
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-throw-other-exception")
    public String testHasTransactionThrowOtherException() throws Exception {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionThrowOtherException(user, role);
        return "OK";
    }

    /**
     * 条件：手动抛出自定义的异常
     *      注： 即使已经自定义了异常并且在@Transactional中rollbackFor指定了， spring还是会会捕获RuntimeException和ErrorException
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-throw-custom-exception")
    public String testHasTransactionThrowCustomException() throws Exception {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionThrowCustomException(user, role);
        return "OK";
    }

    /**
     * 条件：捕获手动抛出自定义的异常
     * 现象：
     * 原因：
     *
     * @author tanggq
     * @date 2022/4/13
     **/
    @GetMapping("/has-transaction-catch-custom-exception")
    public String testHasTransactionCatchCustomException() throws Exception {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionCatchCustomException(user, role);
        return "OK";
    }

    @GetMapping("/has-transaction-with-template")
    public String testHasTransactionWithTemplate() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithTemplate(user, role);
        return "OK";
    }

}
