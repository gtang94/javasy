package io.github.gtang94.springboot.mybatis.controller;

import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/list")
    public HashMap<String, String> list() {

        HashMap<String, String> hashMapData = roleDao.getHashMapData("1,2");
//        HashMap hashMapData = new HashMap();
//        hashMapData.put("1", "aaa");
//        hashMapData.put("2", "bbb");
        return hashMapData;
    }
}
