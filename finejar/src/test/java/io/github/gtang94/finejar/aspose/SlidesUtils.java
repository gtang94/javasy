package io.github.gtang94.finejar.aspose;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SlidesUtils {

    public static void main(String[] args) {
        String sourceFile = "D:\\workspace\\lib\\test\\FB1001.pptx";
        String targetFile = "D:\\workspace\\lib\\test\\FB1001.pdf";

        FileOutputStream os = null;
        try {
            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
            License license = new License();
            license.setLicense(is);

            long old = System.currentTimeMillis();
            os = new FileOutputStream(targetFile);
            Presentation ppt = new Presentation(sourceFile);//加载源文件数据
            ppt.save(os, com.aspose.slides.SaveFormat.Pdf);//设置转换文件类型并转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
