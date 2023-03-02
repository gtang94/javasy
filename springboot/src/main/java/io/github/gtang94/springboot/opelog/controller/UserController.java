package io.github.gtang94.springboot.opelog.controller;

import io.github.gtang94.springboot.opelog.annotation.OperateLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/mod")
    @OperateLog("编辑user")
    public String modUser() {
        return "tanggq";
    }
}
