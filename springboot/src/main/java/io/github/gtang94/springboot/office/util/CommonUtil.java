package io.github.gtang94.springboot.office.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * @author tanggq
 * @class CommonUtil
 * @description
 * @date 2021/5/7
 **/
public class CommonUtil {

    public static boolean createWord(Map data, String templateDir, String templateName, String filePath, String fileName) {
        try {
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(templateDir));

            Template template = configuration.getTemplate(templateName);
            File outFile = new File(filePath + File.separator + fileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
