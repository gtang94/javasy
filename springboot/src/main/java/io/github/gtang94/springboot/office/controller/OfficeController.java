package io.github.gtang94.springboot.office.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.github.gtang94.springboot.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanggq
 * @class OfficeController
 * @description
 * @date 2021/5/7
 **/
@Controller
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping("/word/download")
    public JSONPObject wordDownload() {
        officeService.wordDownload();
        return null;
    }
}
