package io.github.gtang94.springboot.hutool.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanggq
 * @class FileDownload hutool工具包文件下载
 * @description
 * @date 2021/5/20
 **/
@RestController
@RequestMapping("/hutool")
public class FileDownload {

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "D:/zh/pdf/test.pdf";

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = FileUtil.readBytes(filePath);
        response.setContentType("application/octet-stream");
        String filename = new String("test.pdf".getBytes("UTF-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        outputStream.write(bytes);
        IoUtil.close(outputStream);
    }

}
