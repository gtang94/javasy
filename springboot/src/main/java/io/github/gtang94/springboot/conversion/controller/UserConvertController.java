package io.github.gtang94.springboot.conversion.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.gtang94.springboot.conversion.entity.User;
import io.github.gtang94.springboot.conversion.utils.XmlConvertUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @author tanggq
 * @class UserController
 * @description
 * @date 2021/6/3
 **/
@RestController
@RequestMapping("/convert/user")
public class UserConvertController {

    @GetMapping("/bean")
    public String getBean() {

        XmlMapper builder = XmlConvertUtil.xmlBuilder();
        ClassPathResource classPathResource = new ClassPathResource("./user.xml");
        try {
            File file = classPathResource.getFile();
            User user = (User) XmlConvertUtil.xml2JavaBean(builder, file);
            System.err.println(user.toString());

            String xml = XmlConvertUtil.bean2Xml(builder, user);
            System.err.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
