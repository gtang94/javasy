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

    @GetMapping("/no-transaction")
    public String testNoTransaction() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.saveTwoTableNoTransaction(user, role);
        return "OK";
    }

    @GetMapping("/has-transaction")
    public String testHasTransaction() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.saveTwoTableHasTransaction(user, role);
        return "OK";
    }

    @GetMapping("/has-transaction-with-private")
    public String testHasTransactionWithPrivate() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPrivate(user, role);
        return "OK";
    }

    @GetMapping("/has-transaction-with-private2")
    public String testHasTransactionWithPrivate2() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPrivate2(user, role);
        return "OK";
    }

    @GetMapping("/has-transaction-with-final")
    public String testHasTransactionWithFinal() {
        User user = new User(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());
        Role role = new Role(RandomUtil.randomLong(999999999), IdUtil.fastSimpleUUID());

        userService.testHasTransactionWithPrivate(user, role);
        return "OK";
    }
}
