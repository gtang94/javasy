package io.github.gtang94.finejar.aspose.util;

import com.aspose.cells.License;
import com.aspose.cells.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CellsUtils {

    public static void main(String[] args) {
        String sourceFile = "D:\\workspace\\lib\\test\\20220208_2.xlsx";
        String targetFile = "D:\\workspace\\lib\\test\\20220208_2.pdf";

        FileOutputStream os = null;
        try {
            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
            License license = new License();
            license.setLicense(is);

            long old = System.currentTimeMillis();
            os = new FileOutputStream(targetFile);
            Workbook excel = new Workbook(sourceFile);//加载源文件数据
            excel.save(os, com.aspose.cells.SaveFormat.PDF);//设置转换文件类型并转换
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
