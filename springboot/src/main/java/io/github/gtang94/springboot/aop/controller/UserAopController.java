package io.github.gtang94.springboot.aop.controller;

import io.github.gtang94.springboot.aop.common.ControllerWebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanggq
 * @class UserController
 * @description
 * @date 2021/4/22
 **/
@RestController
@RequestMapping("/user")
public class UserAopController {

    @GetMapping("/getOne")
    @ControllerWebLog(name = "查询", intoDB = true)
    public String getOne(Long id, String name) {
        return "11111";
    }
}
