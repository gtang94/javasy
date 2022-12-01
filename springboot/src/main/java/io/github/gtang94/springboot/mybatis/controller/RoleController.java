package io.github.gtang94.springboot.mybatis.controller;

import cn.hutool.json.JSONObject;
import io.github.gtang94.springboot.mybatis.bean.Person;
import io.github.gtang94.springboot.mybatis.bean.Role;
import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import io.github.gtang94.springboot.mybatis.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * @author tanggq
 * @class UserController
 * @description
 * @date 2021/5/19
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleDao roleDao;

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public HashMap<String, String> list() {

        HashMap<String, String> hashMapData = roleDao.getHashMapData("1,2");
//        HashMap hashMapData = new HashMap();
//        hashMapData.put("1", "aaa");
//        hashMapData.put("2", "bbb");
        return hashMapData;
    }

    @GetMapping("/test")
    public Person test() {
        Person person = new Person();
        person.setName("name");
        person.setBirthday(new Date("2021/5/31"));
        return person;
    }

    @GetMapping("/show")
    public Role show() {

        return roleService.show();
    }
}
