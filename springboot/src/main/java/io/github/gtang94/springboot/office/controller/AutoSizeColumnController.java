package io.github.gtang94.springboot.office.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.github.gtang94.springboot.office.service.AutoSizeColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 *
 *
 * 系统速度优化
 * 1.排查思路
 * 浏览器开发者模式的网络栏中，检查每个请求的加载情况，包括：加载数据量大小、请求发送时长、等待响应时长、内容下载时长。
 * 根据各个值做响应的优化
 * 2.处理思路
 * 请求发送时间长一般很少，一般这个值大是在上传文件等情况下
 * 内容下载时间长一般是服务器带宽较小、或返回字段太多，优化处理：增加服务器带宽、不必要的字段不返回前端
 * 等待响应时间长一般是程序逻辑相关的问题。可能是SQL慢，可能是循环中的问题。
 * 高CPU排查
 *
 */
@Controller
@RequestMapping("/office")
public class AutoSizeColumnController {

    @Resource
    private AutoSizeColumnService autoSizeColumnService;

    @GetMapping("/auto-size-column-excel/download")
    public JSONPObject wordDownload() {
        String filename = "D://tmp/auto-size-column-file.xlsx";

        try {
            autoSizeColumnService.download(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
