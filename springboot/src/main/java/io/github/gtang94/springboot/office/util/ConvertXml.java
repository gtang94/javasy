package io.github.gtang94.springboot.office.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;

import java.util.List;

/**
 * @author tanggq
 * @class ConvertXml
 * @description
 * @date 2021/5/7
 **/
public class ConvertXml {

    public static void main(String[] args) throws Exception {
        formatXml("C:\\Users\\olivi\\Downloads\\twt.ftl", "C:\\Users\\olivi\\Downloads\\twt.ftl");
    }

    public static void formatXml(String src, String dst) throws Exception {
        FileReader fileReader = new FileReader(src);

        List<String> strings = fileReader.readLines();
        FileAppender appender = new FileAppender(FileUtil.newFile(dst), 16, true);

        for (String string : strings) {
            if (!string.contains("$")) {
                appender.append(string);
                continue;
            }
            string = string.replaceAll("\\$", "#\\$");
            String[] ss = string.split("#");
            // 同一行的内容写到同一行,文件追加自动换行了
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                String s1 = ss[i];
                if (!s1.startsWith("$")) {
                    sb.append(s1);
                    continue;
                }
                int i1 = s1.lastIndexOf("}");
                String substr = s1.substring(0, i1 + 1);
                sb.append(substr.replaceAll("<[^>]+>", ""));
                sb.append(s1.substring(i1 + 1));
            }
            appender.append(sb.toString());
        }
        appender.flush();
        appender.toString();
    }
}
