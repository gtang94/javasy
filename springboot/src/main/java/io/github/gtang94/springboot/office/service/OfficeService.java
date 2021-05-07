package io.github.gtang94.springboot.office.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.gtang94.springboot.office.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanggq
 * @class OfficeService
 * @description
 * @date 2021/5/7
 **/

@Service
public class OfficeService {

    public void wordDownload() {
        // TODO: generator
        generatorWrod();
        // TODO: download
    }

    private void generatorWrod() {
        Map data = new HashMap();
        data.put("projectName", "测试名称");
        data.put("inspectionOrganizationContact", "长电话2333333333333333333333333333333333333333333");

        data.put("conclusion1", "conclusion1");
        data.put("conclusion2", "conclusion1");
        data.put("conclusion3", "conclusion1");
        data.put("conclusion4", "conclusion1");
        data.put("conclusion5", "conclusion1");
        data.put("conclusion6", "conclusion1");
        data.put("conclusion7", "conclusion1");
        data.put("conclusion8", "conclusion1");
        data.put("conclusion8", "conclusion1");
        data.put("conclusion10", "conclusion1");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(
                "http://www.baidu.com",
                300,
                300,
                ImgUtil.IMAGE_TYPE_PNG,
                outputStream);
        byte[] pngData = outputStream.toByteArray();
//        Base64.encode(pngData);
        data.put("qcUrl", Base64.encode(pngData));


        String templateDir = "C:\\Users\\olivi\\Downloads\\";
        String templateName = "twt.ftl";
        String filePath = "C:\\Users\\olivi\\Downloads\\";
        String fileName = "twt-new.doc";

        CommonUtil.createWord(data,templateDir, templateName, filePath, fileName);
    }


    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
//        configuration.setClassForTemplateLoading(OfficeService.class, "/templates/");
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\olivi\\Downloads\\"));
        Template template = configuration.getTemplate("233.ftl");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name","gaolei");
        dataMap.put("id","02201");
        dataMap.put("code","251525v");
        dataMap.put("pwd","root");
        dataMap.put("tel","08583552");
        File outFile = new File("UserInfoTest.doc");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        try {
            template.process(dataMap,out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
