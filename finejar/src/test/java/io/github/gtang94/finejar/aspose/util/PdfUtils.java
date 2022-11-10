package io.github.gtang94.finejar.aspose.util;

import com.aspose.pdf.License;

import java.io.*;

public class PdfUtils {

    public static void main(String[] args) {
        String sourceFile = "D:\\workspace\\lib\\test\\TheBrain_12_TransitionGuide_V8.pdf";
        String targetFile = "D:\\workspace\\lib\\test\\TheBrain_12_TransitionGuide_V8.docx";

        FileOutputStream os = null;
        try {
            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
            License license = new License();
            license.setLicense(is);

            os = new FileOutputStream(targetFile);
            com.aspose.pdf.Document doc = new com.aspose.pdf.Document(sourceFile);//加载源文件数据
            doc.save(os, com.aspose.pdf.SaveFormat.DocX);//设置转换文件类型并转换
            os.close();
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
