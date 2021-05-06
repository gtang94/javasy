package io.github.gtang94.finejar.mini;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import org.junit.Test;

import java.util.List;

/**
 * @class: ConvertFtl
 * @description:
 * @author: tanggq
 * @date: 5/7/21
 * @version: 1.0
 */
public class ConvertFtl {

    @Test
    public void formatXml() throws Exception {
        FileReader fileReader = new FileReader("/Users/tangguoqiang/Desktop/zhonghe-doc/报告模板/塔 式 起 重 机 委 托 检 验 报 告.xml");

        List<String> strings = fileReader.readLines();
        FileAppender appender = new FileAppender(FileUtil.newFile("/Users/tangguoqiang/Desktop/zhonghe-doc/报告模板/塔 式 起 重 机 委 托 检 验 报 告2.xml"), 16, true);

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
