package io.github.gtang94.springboot.rely.controller;

import io.github.gtang94.springboot.rely.methodA.SetterReply1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanggq
 * @class ReplyApplication
 * @description
 * @date 2022/2/28
 **/
@RestController
@RequestMapping("/reply")
public class ReplyApplication {

    @Autowired
    private SetterReply1 a1;

    @GetMapping("/test")
    public String test(Long id, String name) {
        return "11111";
    }
}
